package com.learningtracker.service;

import com.learningtracker.model.Course;
import com.learningtracker.model.Enrollment;
import com.learningtracker.model.User;
import com.learningtracker.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для работы с записями на курсы
 * Управление записью студентов на курсы
 */
@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserService userService;
    private final CourseService courseService;

    /**
     * Записать студента на курс
     */
    @Transactional
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        User student = userService.getUserById(studentId);
        Course course = courseService.getCourseById(courseId);

        // Проверка роли пользователя
        if (student.getRole() != User.Role.STUDENT) {
            throw new RuntimeException("User is not a student");
        }

        // Проверка на существующую запись
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new RuntimeException("Student is already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus(Enrollment.EnrollmentStatus.ACTIVE);

        return enrollmentRepository.save(enrollment);
    }

    /**
     * Получить запись по ID
     */
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
    }

    /**
     * Получить все записи студента
     */
    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    /**
     * Получить активные записи студента
     */
    public List<Enrollment> getActiveEnrollmentsByStudent(Long studentId) {
        User student = userService.getUserById(studentId);
        return enrollmentRepository.findByStudentAndStatus(student, Enrollment.EnrollmentStatus.ACTIVE);
    }

    /**
     * Получить все записи на курс
     */
    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    /**
     * Обновить статус записи
     */
    @Transactional
    public Enrollment updateEnrollmentStatus(Long id, Enrollment.EnrollmentStatus status) {
        Enrollment enrollment = getEnrollmentById(id);
        enrollment.setStatus(status);
        return enrollmentRepository.save(enrollment);
    }

    /**
     * Отписать студента от курса (изменить статус на DROPPED)
     */
    @Transactional
    public Enrollment dropEnrollment(Long id) {
        return updateEnrollmentStatus(id, Enrollment.EnrollmentStatus.DROPPED);
    }

    /**
     * Полностью удалить запись
     */
    @Transactional
    public void deleteEnrollment(Long id) {
        Enrollment enrollment = getEnrollmentById(id);
        enrollmentRepository.delete(enrollment);
    }

    /**
     * Получить все записи
     */
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }
}
