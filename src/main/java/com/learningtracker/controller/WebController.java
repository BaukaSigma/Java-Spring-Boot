package com.learningtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для веб-страниц (Thymeleaf)
 * Обрабатывает запросы к HTML страницам
 */
@Controller
public class WebController {

    /**
     * Главная страница
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Страница входа
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Страница регистрации
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * Панель управления (перенаправляет на роль-специфичную)
     */
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    /**
     * Панель студента
     */
    @GetMapping("/student-dashboard")
    public String studentDashboard() {
        return "student-dashboard";
    }

    /**
     * Панель преподавателя
     */
    @GetMapping("/instructor-dashboard")
    public String instructorDashboard() {
        return "instructor-dashboard";
    }

    /**
     * Панель администратора
     */
    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }
}
