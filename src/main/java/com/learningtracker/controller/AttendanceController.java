package com.learningtracker.controller;

import com.learningtracker.dto.request.AttendanceRequest;
import com.learningtracker.dto.response.MessageResponse;
import com.learningtracker.model.Attendance;
import com.learningtracker.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Контроллер для управления посещаемостью
 * Эндпоинты: /api/attendance/*
 */
@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AttendanceController {

    private final AttendanceService attendanceService;

    /**
     * GET /api/attendance
     * Получить все записи посещаемости (только для админов)
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    /**
     * GET /api/attendance/{id}
     * Получить запись посещаемости по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getAttendanceById(@PathVariable Long id) {
        try {
            Attendance attendance = attendanceService.getAttendanceById(id);
            return ResponseEntity.ok(attendance);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /api/attendance/student/{studentId}
     * Получить посещаемость студента
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getAttendanceByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByStudent(studentId));
    }

    /**
     * GET /api/attendance/enrollment/{enrollmentId}
     * Получить посещаемость по записи
     */
    @GetMapping("/enrollment/{enrollmentId}")
    public ResponseEntity<List<Attendance>> getAttendanceByEnrollment(@PathVariable Long enrollmentId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByEnrollment(enrollmentId));
    }

    /**
     * GET /api/attendance/course/{courseId}
     * Получить посещаемость курса
     */
    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<List<Attendance>> getAttendanceByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByCourse(courseId));
    }

    /**
     * GET /api/attendance/course/{courseId}/date
     * Получить посещаемость курса на конкретную дату
     */
    @GetMapping("/course/{courseId}/date")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<List<Attendance>> getAttendanceByCourseAndDate(
            @PathVariable Long courseId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return ResponseEntity.ok(attendanceService.getAttendanceByCourseAndDate(courseId, date));
    }

    /**
     * POST /api/attendance
     * Отметить посещаемость (только для преподавателей и админов)
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<?> recordAttendance(@Valid @RequestBody AttendanceRequest request) {
        try {
            Attendance attendance = attendanceService.recordAttendance(request);
            return ResponseEntity.ok(attendance);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }

    /**
     * PUT /api/attendance/{id}
     * Обновить посещаемость (только для преподавателей и админов)
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<?> updateAttendance(
            @PathVariable Long id,
            @Valid @RequestBody AttendanceRequest request
    ) {
        try {
            Attendance attendance = attendanceService.updateAttendance(id, request);
            return ResponseEntity.ok(attendance);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }

    /**
     * DELETE /api/attendance/{id}
     * Удалить запись посещаемости (только для преподавателей и админов)
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long id) {
        try {
            attendanceService.deleteAttendance(id);
            return ResponseEntity.ok(new MessageResponse("Attendance record deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(e.getMessage())
            );
        }
    }
}
