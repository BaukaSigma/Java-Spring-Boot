package com.learningtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Модель задания/домашней работы
 * Многоязычная поддержка для названия и описания
 */
@Entity
@Table(name = "assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Курс, к которому относится задание
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    /**
     * Название задания на английском
     */
    @Column(nullable = false)
    @NotBlank(message = "English title is required")
    private String titleEn;

    /**
     * Название задания на русском
     */
    @Column(nullable = false)
    @NotBlank(message = "Russian title is required")
    private String titleRu;

    /**
     * Название задания на казахском
     */
    @Column(nullable = false)
    @NotBlank(message = "Kazakh title is required")
    private String titleKk;

    /**
     * Описание задания на английском
     */
    @Column(columnDefinition = "TEXT")
    private String descriptionEn;

    /**
     * Описание задания на русском
     */
    @Column(columnDefinition = "TEXT")
    private String descriptionRu;

    /**
     * Описание задания на казахском
     */
    @Column(columnDefinition = "TEXT")
    private String descriptionKk;

    /**
     * Максимальный балл за задание
     */
    @Column(nullable = false, precision = 5, scale = 2)
    @NotNull(message = "Max score is required")
    @Positive(message = "Max score must be positive")
    private BigDecimal maxScore;

    /**
     * Срок сдачи задания
     */
    @Column
    private LocalDateTime dueDate;

    /**
     * Дата создания задания
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /**
     * Оценки студентов за это задание
     */
    @JsonIgnore
    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Grade> grades = new HashSet<>();

    /**
     * Получить название задания на указанном языке
     */
    public String getTitle(String language) {
        return switch (language.toLowerCase()) {
            case "ru" -> titleRu;
            case "kk" -> titleKk;
            default -> titleEn;
        };
    }

    /**
     * Получить описание задания на указанном языке
     */
    public String getDescription(String language) {
        return switch (language.toLowerCase()) {
            case "ru" -> descriptionRu;
            case "kk" -> descriptionKk;
            default -> descriptionEn;
        };
    }

    /**
     * Проверить, просрочено ли задание
     */
    public boolean isOverdue() {
        return dueDate != null && LocalDateTime.now().isAfter(dueDate);
    }
}
