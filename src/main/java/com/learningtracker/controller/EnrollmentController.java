package com.learningtracker.controller;

import com.learningtracker.dto.response.EnrollmentResponse;
import com.learningtracker.dto.response.MessageResponse;
import com.learningtracker.model.Enrollment;
import com.learningtracker.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер для управления записями на курсы
 * Эндпоинты: /api/enrollments/*
 */
@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    /**
     * POST /api/enrollments
     * Записать студента на курс
     */
    @PostMapping
    public ResponseEntity<?> enrollStudent(
            @RequestParam Long studentId,
            @RequestParam Long courseId
    ) {
        try {
            Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
            return ResponseEntity.ok(enrollment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }

    /**
     * GET /api/enrollments/{id}
     * Получить запись по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getEnrollmentById(@PathVariable Long id) {
        try {
            Enrollment enrollment = enrollmentService.getEnrollmentById(id);
            return ResponseEntity.ok(enrollment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /api/enrollments/student/{studentId}
     * Получить все записи студента
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        List<EnrollmentResponse> enrollments = enrollmentService.getEnrollmentsByStudent(studentId)
            .stream()
            .map(EnrollmentResponse::fromEnrollment)
            .collect(Collectors.toList());
        return ResponseEntity.ok(enrollments);
    }

    /**
     * GET /api/enrollments/student/{studentId}/active
     * Получить активные записи студента
     */
    @GetMapping("/student/{studentId}/active")
    public ResponseEntity<List<Enrollment>> getActiveEnrollmentsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getActiveEnrollmentsByStudent(studentId));
    }

    /**
     * GET /api/enrollments/course/{courseId}
     * Получить все записи на курс
     */
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourse(courseId));
    }

    /**
     * GET /api/enrollments
     * Получить все записи (только для админов)
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    /**
     * PUT /api/enrollments/{id}/status
     * Обновить статус записи
     */
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<?> updateEnrollmentStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        try {
            Enrollment.EnrollmentStatus enrollmentStatus = Enrollment.EnrollmentStatus.valueOf(status.toUpperCase());
            Enrollment enrollment = enrollmentService.updateEnrollmentStatus(id, enrollmentStatus);
            return ResponseEntity.ok(enrollment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Invalid status: " + status)
            );
        }
    }

    /**
     * DELETE /api/enrollments/{id}/drop
     * Отписать студента от курса
     */
    @DeleteMapping("/{id}/drop")
    public ResponseEntity<?> dropEnrollment(@PathVariable Long id) {
        try {
            Enrollment enrollment = enrollmentService.dropEnrollment(id);
            return ResponseEntity.ok(new MessageResponse("Enrollment dropped successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }

    /**
     * DELETE /api/enrollments/{id}
     * Полностью удалить запись (только для админов)
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEnrollment(@PathVariable Long id) {
        try {
            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.ok(new MessageResponse("Enrollment deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }
}
