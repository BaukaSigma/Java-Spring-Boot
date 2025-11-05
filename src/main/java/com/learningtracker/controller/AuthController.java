package com.learningtracker.controller;

import com.learningtracker.dto.request.LoginRequest;
import com.learningtracker.dto.request.RegisterRequest;
import com.learningtracker.dto.response.AuthResponse;
import com.learningtracker.dto.response.MessageResponse;
import com.learningtracker.model.User;
import com.learningtracker.service.JwtService;
import com.learningtracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для аутентификации и регистрации
 * Эндпоинты: /api/auth/*
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    /**
     * POST /api/auth/register
     * Регистрация нового пользователя
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        try {
            User user = userService.registerUser(request);
            
            return ResponseEntity.ok(new MessageResponse(
                    "User registered successfully! Username: " + user.getUsername()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }

    /**
     * POST /api/auth/login
     * Вход в систему (получение JWT токена)
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest request) {
        try {
            // Аутентификация пользователя
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Генерация JWT токена
            String jwt = jwtService.generateToken(request.getUsername());

            // Получение информации о пользователе
            User user = userService.getUserByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return ResponseEntity.ok(new AuthResponse(
                    jwt,
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRole().name()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Invalid username or password")
            );
        }
    }

    /**
     * GET /api/auth/me
     * Получить информацию о текущем пользователе
     */
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(401).body(
                    new MessageResponse("Not authenticated")
            );
        }

        String username = authentication.getName();
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(user);
    }
}
