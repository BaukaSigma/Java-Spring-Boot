package com.learningtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Модель оценки студента за задание
 * Хранит баллы и обратную связь на трех языках
 */
@Entity
@Table(name = "grades", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"enrollment_id", "assignment_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Запись студента на курс
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    /**
     * Задание, за которое выставлена оценка
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    /**
     * Баллы, полученные студентом
     */
    @Column(precision = 5, scale = 2)
    private BigDecimal score;

    /**
     * Дата и время отправки задания студентом
     */
    @Column
    private LocalDateTime submittedAt;

    /**
     * Дата и время выставления оценки преподавателем
     */
    @CreationTimestamp
    @Column
    private LocalDateTime gradedAt;

    /**
     * Обратная связь от преподавателя на английском
     */
    @Column(columnDefinition = "TEXT")
    private String feedbackEn;

    /**
     * Обратная связь от преподавателя на русском
     */
    @Column(columnDefinition = "TEXT")
    private String feedbackRu;

    /**
     * Обратная связь от преподавателя на казахском
     */
    @Column(columnDefinition = "TEXT")
    private String feedbackKk;

    /**
     * Получить обратную связь на указанном языке
     */
    public String getFeedback(String language) {
        return switch (language.toLowerCase()) {
            case "ru" -> feedbackRu;
            case "kk" -> feedbackKk;
            default -> feedbackEn;
        };
    }

    /**
     * Вычислить процент выполнения задания
     */
    public Double getPercentage() {
        if (score == null || assignment == null || assignment.getMaxScore() == null) {
            return null;
        }
        return score.divide(assignment.getMaxScore(), 4, BigDecimal.ROUND_HALF_UP)
            .multiply(new BigDecimal("100"))
            .doubleValue();
    }
}
