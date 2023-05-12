package com.example.community;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mongoConfig {
    private static final String URI = "mongodb://localhost:27017";
    private static final String DATABASE = "member";

    @Bean
    public MongoDatabase mongoDatabase(){
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        return database;

    }

}
