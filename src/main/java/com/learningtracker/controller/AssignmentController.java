package com.learningtracker.controller;

import com.learningtracker.dto.request.AssignmentRequest;
import com.learningtracker.dto.response.MessageResponse;
import com.learningtracker.model.Assignment;
import com.learningtracker.service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления заданиями
 * Эндпоинты: /api/assignments/*
 */
@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentController {

    private final AssignmentService assignmentService;

    /**
     * GET /api/assignments
     * Получить все задания (только для админов)
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

    /**
     * GET /api/assignments/{id}
     * Получить задание по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getAssignmentById(@PathVariable Long id) {
        try {
            Assignment assignment = assignmentService.getAssignmentById(id);
            return ResponseEntity.ok(assignment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /api/assignments/course/{courseId}
     * Получить все задания курса
     */
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByCourse(courseId));
    }

    /**
     * GET /api/assignments/course/{courseId}/upcoming
     * Получить предстоящие задания курса
     */
    @GetMapping("/course/{courseId}/upcoming")
    public ResponseEntity<List<Assignment>> getUpcomingAssignments(@PathVariable Long courseId) {
        return ResponseEntity.ok(assignmentService.getUpcomingAssignments(courseId));
    }

    /**
     * POST /api/assignments
     * Создать новое задание (только для преподавателей и админов)
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<?> createAssignment(@Valid @RequestBody AssignmentRequest request) {
        try {
            Assignment assignment = assignmentService.createAssignment(request);
            return ResponseEntity.ok(assignment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }

    /**
     * PUT /api/assignments/{id}
     * Обновить задание (только для преподавателей и админов)
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<?> updateAssignment(
            @PathVariable Long id,
            @Valid @RequestBody AssignmentRequest request
    ) {
        try {
            Assignment assignment = assignmentService.updateAssignment(id, request);
            return ResponseEntity.ok(assignment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }

    /**
     * DELETE /api/assignments/{id}
     * Удалить задание (только для преподавателей и админов)
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long id) {
        try {
            assignmentService.deleteAssignment(id);
            return ResponseEntity.ok(new MessageResponse("Assignment deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }
}
