package com.learningtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Модель пользователя системы
 * Представляет студентов, преподавателей и администраторов
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Уникальное имя пользователя для входа в систему
     */
    @Column(unique = true, nullable = false, length = 100)
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 100, message = "Username must be between 3 and 100 characters")
    private String username;

    /**
     * Email пользователя (уникальный)
     */
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    /**
     * Хешированный пароль (хранится в зашифрованном виде через BCrypt)
     */
    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    private String password;

    /**
     * Имя пользователя
     */
    @Column(length = 100)
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(length = 100)
    private String lastName;

    /**
     * Роль пользователя в системе
     * Возможные значения: STUDENT, INSTRUCTOR, ADMIN
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Role role;

    /**
     * Дата и время создания учетной записи
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /**
     * Дата и время последнего обновления учетной записи
     */
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /**
     * Курсы, которые преподает этот пользователь (если он преподаватель)
     */
    @JsonIgnore
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Course> taughtCourses = new HashSet<>();

    /**
     * Записи на курсы (если пользователь - студент)
     */
    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Enrollment> enrollments = new HashSet<>();

    /**
     * Перечисление ролей пользователей
     */
    public enum Role {
        STUDENT,      // Студент
        INSTRUCTOR,   // Преподаватель
        ADMIN         // Администратор
    }

    /**
     * Получить полное имя пользователя
     */
    public String getFullName() {
        if (firstName != null && lastName != null) {
            return firstName + " " + lastName;
        } else if (firstName != null) {
            return firstName;
        } else if (lastName != null) {
            return lastName;
        }
        return username;
    }
}
