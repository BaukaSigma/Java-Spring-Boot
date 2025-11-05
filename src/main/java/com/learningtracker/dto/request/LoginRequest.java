package com.learningtracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO для запроса входа в систему
 */
@Data
public class LoginRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
