package com.learningtracker.controller;

import com.learningtracker.dto.response.MessageResponse;
import com.learningtracker.model.User;
import com.learningtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления пользователями (только для ADMIN)
 * Эндпоинты: /api/users/*
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    /**
     * GET /api/users
     * Получить всех пользователей (только для ADMIN)
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * GET /api/users/{id}
     * Получить пользователя по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /api/users/role/{role}
     * Получить пользователей по роли
     */
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        try {
            User.Role userRole = User.Role.valueOf(role.toUpperCase());
            return ResponseEntity.ok(userService.getUsersByRole(userRole));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /api/users/students
     * Получить всех студентов
     */
    @GetMapping("/students")
    public ResponseEntity<List<User>> getAllStudents() {
        return ResponseEntity.ok(userService.getAllStudents());
    }

    /**
     * GET /api/users/instructors
     * Получить всех преподавателей
     */
    @GetMapping("/instructors")
    public ResponseEntity<List<User>> getAllInstructors() {
        return ResponseEntity.ok(userService.getAllInstructors());
    }

    /**
     * PUT /api/users/{id}
     * Обновить пользователя
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }

    /**
     * DELETE /api/users/{id}
     * Удалить пользователя (только для ADMIN)
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(new MessageResponse("User deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }

    /**
     * GET /api/users/search
     * Поиск пользователей
     */
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String query) {
        return ResponseEntity.ok(userService.searchUsers(query));
    }
}
