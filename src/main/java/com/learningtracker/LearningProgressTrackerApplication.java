package com.learningtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс приложения Learning Progress Tracker
 * Точка входа в Spring Boot приложение
 * 
 * @author Your Name
 * @version 1.0
 */
@SpringBootApplication
public class LearningProgressTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningProgressTrackerApplication.class, args);
        System.out.println("\n========================================");
        System.out.println("✓ Learning Progress Tracker запущен!");
        System.out.println("✓ Сервер: http://localhost:8080");
        System.out.println("✓ API Endpoint: http://localhost:8080/api");
        System.out.println("========================================\n");
    }
}
