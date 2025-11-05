package com.learningtracker.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO для создания и обновления оценки
 */
@Data
public class GradeRequest {

    @NotNull(message = "Enrollment ID is required")
    private Long enrollmentId;

    @NotNull(message = "Assignment ID is required")
    private Long assignmentId;

    private BigDecimal score;

    private LocalDateTime submittedAt;

    private String feedbackEn;
    private String feedbackRu;
    private String feedbackKk;
}
