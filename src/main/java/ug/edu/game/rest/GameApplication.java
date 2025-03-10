package ug.edu.game.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ug.edu.game.rest.service.DataBaseInitializer;

@SpringBootApplication
public class GameApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialize(DataBaseInitializer dataBaseInitializer) {
        return args -> {
            dataBaseInitializer.initializeDatabase();
        };
    }
}
