package com.learningtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Модель курса
 * Представляет учебный курс с многоязычной поддержкой
 */
@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Уникальный код курса (например: CS101, MATH201)
     */
    @Column(unique = true, nullable = false, length = 50)
    @NotBlank(message = "Course code is required")
    private String courseCode;

    /**
     * Название курса на английском языке
     */
    @Column(nullable = false)
    @NotBlank(message = "English title is required")
    private String titleEn;

    /**
     * Название курса на русском языке
     */
    @Column(nullable = false)
    @NotBlank(message = "Russian title is required")
    private String titleRu;

    /**
     * Название курса на казахском языке
     */
    @Column(nullable = false)
    @NotBlank(message = "Kazakh title is required")
    private String titleKk;

    /**
     * Описание курса на английском языке
     */
    @Column(columnDefinition = "TEXT")
    private String descriptionEn;

    /**
     * Описание курса на русском языке
     */
    @Column(columnDefinition = "TEXT")
    private String descriptionRu;

    /**
     * Описание курса на казахском языке
     */
    @Column(columnDefinition = "TEXT")
    private String descriptionKk;

    /**
     * Количество кредитов за курс
     */
    @Column(nullable = false)
    @NotNull(message = "Credits are required")
    @Positive(message = "Credits must be positive")
    private Integer credits;

    /**
     * Преподаватель, ведущий курс
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private User instructor;

    /**
     * Дата создания курса
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /**
     * Студенты, записанные на курс
     */
    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Enrollment> enrollments = new HashSet<>();

    /**
     * Задания для этого курса
     */
    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Assignment> assignments = new HashSet<>();

    /**
     * Получить название курса на указанном языке
     * @param language код языка (en, ru, kk)
     * @return название на соответствующем языке
     */
    public String getTitle(String language) {
        return switch (language.toLowerCase()) {
            case "ru" -> titleRu;
            case "kk" -> titleKk;
            default -> titleEn;
        };
    }

    /**
     * Получить описание курса на указанном языке
     * @param language код языка (en, ru, kk)
     * @return описание на соответствующем языке
     */
    public String getDescription(String language) {
        return switch (language.toLowerCase()) {
            case "ru" -> descriptionRu;
            case "kk" -> descriptionKk;
            default -> descriptionEn;
        };
    }
}
