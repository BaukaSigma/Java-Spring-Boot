package com.learningtracker.dto.response;

import com.learningtracker.model.Course;
import com.learningtracker.model.Enrollment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO для возврата информации о записи студента на курс
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponse {
    private Long id;
    private Long studentId;
    private Course course;
    private LocalDateTime enrollmentDate;
    private String status;

    public static EnrollmentResponse fromEnrollment(Enrollment enrollment) {
        EnrollmentResponse response = new EnrollmentResponse();
        response.setId(enrollment.getId());
        response.setStudentId(enrollment.getStudent().getId());
        response.setCourse(enrollment.getCourse());
        response.setEnrollmentDate(enrollment.getEnrollmentDate());
        response.setStatus(enrollment.getStatus().name());
        return response;
    }
}
