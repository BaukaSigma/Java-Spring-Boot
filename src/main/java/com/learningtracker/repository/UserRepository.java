package com.learningtracker.repository;

import com.learningtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с пользователями
 * Предоставляет методы для поиска и сохранения пользователей в БД
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Найти пользователя по имени пользователя
     */
    Optional<User> findByUsername(String username);

    /**
     * Найти пользователя по email
     */
    Optional<User> findByEmail(String email);

    /**
     * Проверить, существует ли пользователь с таким именем
     */
    boolean existsByUsername(String username);

    /**
     * Проверить, существует ли пользователь с таким email
     */
    boolean existsByEmail(String email);

    /**
     * Найти всех пользователей по роли
     */
    List<User> findByRole(User.Role role);

    /**
     * Найти пользователей по имени или фамилии (содержит строку)
     */
    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
        String firstName, String lastName
    );
}
