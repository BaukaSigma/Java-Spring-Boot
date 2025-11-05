package com.learningtracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO для создания и обновления задания
 */
@Data
public class AssignmentRequest {

    @NotNull(message = "Course ID is required")
    private Long courseId;

    @NotBlank(message = "English title is required")
    private String titleEn;

    @NotBlank(message = "Russian title is required")
    private String titleRu;

    @NotBlank(message = "Kazakh title is required")
    private String titleKk;

    private String descriptionEn;
    private String descriptionRu;
    private String descriptionKk;

    @NotNull(message = "Max score is required")
    @Positive(message = "Max score must be positive")
    private BigDecimal maxScore;

    private LocalDateTime dueDate;
}
