package com.learningtracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * DTO для создания и обновления курса
 */
@Data
public class CourseRequest {

    @NotBlank(message = "Course code is required")
    private String courseCode;

    @NotBlank(message = "English title is required")
    private String titleEn;

    @NotBlank(message = "Russian title is required")
    private String titleRu;

    @NotBlank(message = "Kazakh title is required")
    private String titleKk;

    private String descriptionEn;
    private String descriptionRu;
    private String descriptionKk;

    @NotNull(message = "Credits are required")
    @Positive(message = "Credits must be positive")
    private Integer credits;

    private Long instructorId;
}
