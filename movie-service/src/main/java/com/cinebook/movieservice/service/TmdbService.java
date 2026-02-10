package com.cinebook.movieservice.service;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class TmdbService {

    private final WebClient webClient;

    @Value("${tmdb.api.key}")
    private String apiKey;

    // --- 🧠 SMART CACHE MEMORY ---
    // This variable holds the Real JSON even if the internet disconnects later
    private String lastRealData = null; 

    public TmdbService(@Value("${tmdb.api.base-url}") String baseUrl) {
        // --- ⏱️ TIMEOUT CONFIGURATION (5 Seconds) ---
        // If TMDB takes longer than 5s, we abort and use fallback data.
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofSeconds(5))
                .doOnConnected(conn -> 
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    // --- 🚀 AUTO-STARTUP FETCH ---
    // This runs automatically when the App starts. 
    // It tries to grab real data immediately so it's ready for the user.
    @PostConstruct
    public void seedDataOnStartup() {
        System.out.println("🌱 Startup: Attempting to fetch Real Movie Data...");
        // We call the method, but we don't return anything since it's just seeding
        // The method itself will update 'lastRealData' if successful
        try {
            getNowPlayingMovies(); 
        } catch (Exception e) {
            System.out.println("⚠️ Startup fetch failed (Internet might be down). Will try again on user request.");
        }
    }

    // 1. Get "Now Showing" (With Smart Fallback)
    @Cacheable(value = "nowPlaying", key = "'india'") 
    public String getNowPlayingMovies() {
        try {
            System.out.println("🌍 Connecting to TMDB API...");
            String response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/movie/now_playing")
                            .queryParam("api_key", apiKey)
                            .queryParam("region", "IN")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            
            // ✅ SUCCESS! Save this real data to our memory variable
            if (response != null && !response.isEmpty()) {
                this.lastRealData = response;
                System.out.println("✅ TMDB Success! Data cached in memory.");
            }
            return response;

        } catch (Exception e) {
            System.err.println("⚠️ TMDB Unreachable (" + e.getMessage() + ")");
            
            // 🛡️ FALLBACK STRATEGY
            if (this.lastRealData != null) {
                System.out.println("♻️ Network failed, but using SAVED REAL DATA from memory.");
                return this.lastRealData; // <--- Return the old real movies!
            }
            
            System.err.println("❌ No real data found. Falling back to MOCK DATA.");
            return MOCK_NOW_PLAYING; // Last resort
        }
    }

    // 2. Get Details (With Fallback)
    @Cacheable(value = "movieDetails", key = "#movieId")
    public String getMovieDetails(String movieId) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/movie/" + movieId)
                            .queryParam("api_key", apiKey)
                            .queryParam("append_to_response", "credits,videos,release_dates,images")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            System.err.println("⚠️ TMDB Details Failed. Using Mock.");
            return MOCK_DETAILS;
        }
    }

    // --- MOCK DATA ---
    private static final String MOCK_NOW_PLAYING = """
    {
        "results": [
            { "id": 550, "title": "Fight Club", "poster_path": "/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "vote_average": 8.4, "release_date": "1999-10-15" },
            { "id": 157336, "title": "Interstellar", "poster_path": "/gEU2QniL6E8ahDgTSamDbFicAuD.jpg", "vote_average": 8.6, "release_date": "2014-11-05" },
            { "id": 299534, "title": "Avengers: Endgame", "poster_path": "/or06FN3Dka5tukK1e9sl16pB3iy.jpg", "vote_average": 8.3, "release_date": "2019-04-24" }
        ]
    }
    """;

    private static final String MOCK_DETAILS = """
    {
        "id": 550, "title": "Fight Club (Mock Mode)", "overview": "An insomniac office worker and a devil-may-care soapmaker form an underground fight club...",
        "poster_path": "/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "backdrop_path": "/hZkgoQYus5vegHoetLkCJzb17zJ.jpg",
        "runtime": 139, "vote_average": 8.4,
        "release_dates": { "results": [{ "iso_3166_1": "IN", "release_dates": [{ "certification": "A" }] }] },
        "credits": { "cast": [{ "name": "Edward Norton" }, { "name": "Brad Pitt" }] },
        "videos": { "results": [{ "key": "6JnN1DmbqoU", "type": "Trailer" }] }
    }
    """;
}