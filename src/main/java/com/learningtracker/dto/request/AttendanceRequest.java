package com.learningtracker.dto.request;

import com.learningtracker.model.Attendance;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO для создания и обновления посещаемости
 */
@Data
public class AttendanceRequest {

    @NotNull(message = "Enrollment ID is required")
    private Long enrollmentId;

    @NotNull(message = "Attendance date is required")
    private LocalDate attendanceDate;

    @NotNull(message = "Attendance status is required")
    private Attendance.AttendanceStatus status;

    private String notes;
}
