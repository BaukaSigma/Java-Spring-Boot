package com.learningtracker.controller;

import com.learningtracker.dto.request.GradeRequest;
import com.learningtracker.dto.response.MessageResponse;
import com.learningtracker.model.Grade;
import com.learningtracker.service.GradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления оценками
 * Эндпоинты: /api/grades/*
 */
@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class GradeController {

    private final GradeService gradeService;

    /**
     * GET /api/grades
     * Получить все оценки (только для админов)
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Grade>> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    /**
     * GET /api/grades/{id}
     * Получить оценку по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getGradeById(@PathVariable Long id) {
        try {
            Grade grade = gradeService.getGradeById(id);
            return ResponseEntity.ok(grade);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /api/grades/student/{studentId}
     * Получить все оценки студента
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getGradesByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(gradeService.getGradesByStudent(studentId));
    }

    /**
     * GET /api/grades/enrollment/{enrollmentId}
     * Получить оценки по записи на курс
     */
    @GetMapping("/enrollment/{enrollmentId}")
    public ResponseEntity<List<Grade>> getGradesByEnrollment(@PathVariable Long enrollmentId) {
        return ResponseEntity.ok(gradeService.getGradesByEnrollment(enrollmentId));
    }

    /**
     * GET /api/grades/assignment/{assignmentId}
     * Получить оценки за задание
     */
    @GetMapping("/assignment/{assignmentId}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<List<Grade>> getGradesByAssignment(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(gradeService.getGradesByAssignment(assignmentId));
    }

    /**
     * GET /api/grades/course/{courseId}
     * Получить оценки курса
     */
    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<List<Grade>> getGradesByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(gradeService.getGradesByCourse(courseId));
    }

    /**
     * POST /api/grades
     * Выставить оценку (только для преподавателей и админов)
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<?> createGrade(@Valid @RequestBody GradeRequest request) {
        try {
            Grade grade = gradeService.createGrade(request);
            return ResponseEntity.ok(grade);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }

    /**
     * PUT /api/grades/{id}
     * Обновить оценку (только для преподавателей и админов)
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<?> updateGrade(
            @PathVariable Long id,
            @Valid @RequestBody GradeRequest request
    ) {
        try {
            Grade grade = gradeService.updateGrade(id, request);
            return ResponseEntity.ok(grade);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }

    /**
     * DELETE /api/grades/{id}
     * Удалить оценку (только для преподавателей и админов)
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<?> deleteGrade(@PathVariable Long id) {
        try {
            gradeService.deleteGrade(id);
            return ResponseEntity.ok(new MessageResponse("Grade deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }
}
