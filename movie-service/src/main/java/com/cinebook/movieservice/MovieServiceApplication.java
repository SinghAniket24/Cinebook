package com.cinebook.movieservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching 
public class MovieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
		System.setProperty("java.net.preferIPv4Stack", "true");
	    
	    SpringApplication.run(MovieServiceApplication.class, args);
	}

}
