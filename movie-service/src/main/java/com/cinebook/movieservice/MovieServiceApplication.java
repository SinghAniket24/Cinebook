package com.cinebook.movieservice;

import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

@SpringBootApplication
@EnableCaching 
public class MovieServiceApplication {

    public static void main(String[] args) {
        // Force IPv4 to prevent connection issues on Windows
        System.setProperty("java.net.preferIPv4Stack", "true");
        SpringApplication.run(MovieServiceApplication.class, args);
    }

    /**
     * Enhanced Diagnostic Bean:
     * 1. Pushes a test record.
     * 2. Prints the specific database the app is using.
     * 3. Lists ALL databases on the cluster to find where the data went.
     */
    @Bean
    public CommandLineRunner mongoDiagnostic(MongoTemplate mongoTemplate, MongoClient mongoClient) {
        return args -> {
            try {
                System.out.println("\n>>> [DIAGNOSTIC] Starting Deep MongoDB Discovery...");
                
                // 1. Create a discovery document
                Document discoveryDoc = new Document();
                discoveryDoc.append("event", "Deep Connection Scan");
                discoveryDoc.append("timestamp", new Date());
                discoveryDoc.append("user", "Aniket H. Singh");

                // 2. Save it to force database/collection creation
                mongoTemplate.save(discoveryDoc, "discovery_logs");
                
                System.out.println(">>> [VERIFY] App is currently targeting database: " + mongoTemplate.getDb().getName());

                // 3. Scan the entire cluster for all database names
                System.out.println(">>> [VERIFY] Databases found on your Atlas Cluster:");
                mongoClient.listDatabaseNames().forEach(dbName -> {
                    System.out.println("    - " + dbName);
                });

                System.out.println(">>> [SUCCESS] Scan complete. If 'cinebook_users' is in the list above, the connection is 100% active!\n");
                
            } catch (Exception e) {
                System.err.println("\n>>> [FAILURE] MongoDB Atlas unreachable: " + e.getMessage());
                System.err.println(">>> Action: Ensure IP 0.0.0.0/0 is added to Network Access in Atlas Dashboard.\n");
            }
        };
    }
}