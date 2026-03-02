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
     * Название задания
     */
    @Column(nullable = false)
    @NotBlank(message = "Title is required")
    private String title;

    /**
     * Описание задания
     */
    @Column(columnDefinition = "TEXT")
    private String description;

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
     * Проверить, просрочено ли задание
     */
    public boolean isOverdue() {
        return dueDate != null && LocalDateTime.now().isAfter(dueDate);
    }
}
