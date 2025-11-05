package com.learningtracker.security;

import com.learningtracker.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Фильтр для аутентификации через JWT токены
 * Проверяет каждый запрос на наличие валидного JWT токена
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        
        // Получить заголовок Authorization
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // Проверить наличие токена
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Извлечь токен (убрать "Bearer " префикс)
        jwt = authHeader.substring(7);
        
        try {
            // Извлечь имя пользователя из токена
            username = jwtService.extractUsername(jwt);

            // Если пользователь не аутентифицирован
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Загрузить информацию о пользователе
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Валидировать токен
                if (jwtService.validateToken(jwt, userDetails)) {
                    // Создать аутентификацию
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    // Установить аутентификацию в контекст
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            // Логирование ошибки
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }
}
