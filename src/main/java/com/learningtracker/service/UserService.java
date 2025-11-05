package com.learningtracker.service;

import com.learningtracker.dto.request.RegisterRequest;
import com.learningtracker.model.User;
import com.learningtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с пользователями
 * Управление регистрацией, поиском и обновлением пользователей
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Регистрация нового пользователя
     */
    @Transactional
    public User registerUser(RegisterRequest request) {
        // Проверка на существование пользователя
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Создание нового пользователя
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(User.Role.valueOf(request.getRole().toUpperCase()));

        return userRepository.save(user);
    }

    /**
     * Найти пользователя по ID
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    /**
     * Найти пользователя по имени пользователя
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Найти пользователя по email
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Получить всех пользователей
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Получить пользователей по роли
     */
    public List<User> getUsersByRole(User.Role role) {
        return userRepository.findByRole(role);
    }

    /**
     * Получить всех студентов
     */
    public List<User> getAllStudents() {
        return userRepository.findByRole(User.Role.STUDENT);
    }

    /**
     * Получить всех преподавателей
     */
    public List<User> getAllInstructors() {
        return userRepository.findByRole(User.Role.INSTRUCTOR);
    }

    /**
     * Обновить информацию о пользователе
     */
    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User user = getUserById(id);
        
        if (updatedUser.getFirstName() != null) {
            user.setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            user.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(updatedUser.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
            user.setEmail(updatedUser.getEmail());
        }

        return userRepository.save(user);
    }

    /**
     * Удалить пользователя
     */
    @Transactional
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    /**
     * Поиск пользователей по имени или фамилии
     */
    public List<User> searchUsers(String query) {
        return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query);
    }
}
