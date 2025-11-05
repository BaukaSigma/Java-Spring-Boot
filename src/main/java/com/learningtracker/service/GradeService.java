package com.learningtracker.service;

import com.learningtracker.dto.request.GradeRequest;
import com.learningtracker.model.Assignment;
import com.learningtracker.model.Enrollment;
import com.learningtracker.model.Grade;
import com.learningtracker.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис для работы с оценками
 * Управление выставлением и обновлением оценок студентов
 */
@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final EnrollmentService enrollmentService;
    private final AssignmentService assignmentService;

    /**
     * Выставить оценку
     */
    @Transactional
    public Grade createGrade(GradeRequest request) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(request.getEnrollmentId());
        Assignment assignment = assignmentService.getAssignmentById(request.getAssignmentId());

        // Проверка, что задание относится к курсу, на который записан студент
        if (!enrollment.getCourse().getId().equals(assignment.getCourse().getId())) {
            throw new RuntimeException("Assignment does not belong to the enrolled course");
        }

        // Проверка на существующую оценку
        if (gradeRepository.existsByEnrollmentIdAndAssignmentId(
                request.getEnrollmentId(), request.getAssignmentId())) {
            throw new RuntimeException("Grade already exists for this assignment");
        }

        Grade grade = new Grade();
        grade.setEnrollment(enrollment);
        grade.setAssignment(assignment);
        grade.setScore(request.getScore());
        grade.setSubmittedAt(request.getSubmittedAt());
        grade.setFeedbackEn(request.getFeedbackEn());
        grade.setFeedbackRu(request.getFeedbackRu());
        grade.setFeedbackKk(request.getFeedbackKk());

        return gradeRepository.save(grade);
    }

    /**
     * Получить оценку по ID
     */
    public Grade getGradeById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found with id: " + id));
    }

    /**
     * Получить все оценки студента
     */
    public List<Grade> getGradesByStudent(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    /**
     * Получить оценки студента по записи
     */
    public List<Grade> getGradesByEnrollment(Long enrollmentId) {
        return gradeRepository.findByEnrollmentId(enrollmentId);
    }

    /**
     * Получить оценки за задание
     */
    public List<Grade> getGradesByAssignment(Long assignmentId) {
        return gradeRepository.findByAssignmentId(assignmentId);
    }

    /**
     * Получить оценки курса
     */
    public List<Grade> getGradesByCourse(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    /**
     * Обновить оценку
     */
    @Transactional
    public Grade updateGrade(Long id, GradeRequest request) {
        Grade grade = getGradeById(id);

        if (request.getScore() != null) {
            grade.setScore(request.getScore());
        }
        if (request.getSubmittedAt() != null) {
            grade.setSubmittedAt(request.getSubmittedAt());
        }
        if (request.getFeedbackEn() != null) {
            grade.setFeedbackEn(request.getFeedbackEn());
        }
        if (request.getFeedbackRu() != null) {
            grade.setFeedbackRu(request.getFeedbackRu());
        }
        if (request.getFeedbackKk() != null) {
            grade.setFeedbackKk(request.getFeedbackKk());
        }

        // Обновляем время выставления оценки
        grade.setGradedAt(LocalDateTime.now());

        return gradeRepository.save(grade);
    }

    /**
     * Удалить оценку
     */
    @Transactional
    public void deleteGrade(Long id) {
        Grade grade = getGradeById(id);
        gradeRepository.delete(grade);
    }

    /**
     * Получить все оценки
     */
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }
}
