package com.learningtracker.service;

import com.learningtracker.dto.request.AttendanceRequest;
import com.learningtracker.model.Attendance;
import com.learningtracker.model.Enrollment;
import com.learningtracker.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для работы с посещаемостью
 * Управление записями посещаемости студентов
 */
@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EnrollmentService enrollmentService;

    /**
     * Отметить посещаемость
     */
    @Transactional
    public Attendance recordAttendance(AttendanceRequest request) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(request.getEnrollmentId());

        // Проверка на существующую запись
        if (attendanceRepository.existsByEnrollmentIdAndAttendanceDate(
                request.getEnrollmentId(), request.getAttendanceDate())) {
            throw new RuntimeException("Attendance already recorded for this date");
        }

        Attendance attendance = new Attendance();
        attendance.setEnrollment(enrollment);
        attendance.setAttendanceDate(request.getAttendanceDate());
        attendance.setStatus(request.getStatus());
        attendance.setNotes(request.getNotes());

        return attendanceRepository.save(attendance);
    }

    /**
     * Получить запись посещаемости по ID
     */
    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found with id: " + id));
    }

    /**
     * Получить посещаемость студента
     */
    public List<Attendance> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    /**
     * Получить посещаемость по записи
     */
    public List<Attendance> getAttendanceByEnrollment(Long enrollmentId) {
        return attendanceRepository.findByEnrollmentId(enrollmentId);
    }

    /**
     * Получить посещаемость курса
     */
    public List<Attendance> getAttendanceByCourse(Long courseId) {
        return attendanceRepository.findByCourseId(courseId);
    }

    /**
     * Получить посещаемость курса на конкретную дату
     */
    public List<Attendance> getAttendanceByCourseAndDate(Long courseId, LocalDate date) {
        return attendanceRepository.findByCourseIdAndDate(courseId, date);
    }

    /**
     * Обновить запись посещаемости
     */
    @Transactional
    public Attendance updateAttendance(Long id, AttendanceRequest request) {
        Attendance attendance = getAttendanceById(id);

        if (request.getStatus() != null) {
            attendance.setStatus(request.getStatus());
        }
        if (request.getNotes() != null) {
            attendance.setNotes(request.getNotes());
        }

        return attendanceRepository.save(attendance);
    }

    /**
     * Удалить запись посещаемости
     */
    @Transactional
    public void deleteAttendance(Long id) {
        Attendance attendance = getAttendanceById(id);
        attendanceRepository.delete(attendance);
    }

    /**
     * Получить все записи посещаемости
     */
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
}
