package com.learningtracker.service;

import com.learningtracker.dto.request.AssignmentRequest;
import com.learningtracker.model.Assignment;
import com.learningtracker.model.Course;
import com.learningtracker.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис для работы с заданиями
 * Управление созданием, обновлением и поиском заданий
 */
@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseService courseService;

    /**
     * Создать новое задание
     */
    @Transactional
    public Assignment createAssignment(AssignmentRequest request) {
        Course course = courseService.getCourseById(request.getCourseId());

        Assignment assignment = new Assignment();
        assignment.setCourse(course);
        assignment.setTitleEn(request.getTitleEn());
        assignment.setTitleRu(request.getTitleRu());
        assignment.setTitleKk(request.getTitleKk());
        assignment.setDescriptionEn(request.getDescriptionEn());
        assignment.setDescriptionRu(request.getDescriptionRu());
        assignment.setDescriptionKk(request.getDescriptionKk());
        assignment.setMaxScore(request.getMaxScore());
        assignment.setDueDate(request.getDueDate());

        return assignmentRepository.save(assignment);
    }

    /**
     * Получить задание по ID
     */
    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + id));
    }

    /**
     * Получить все задания курса
     */
    public List<Assignment> getAssignmentsByCourse(Long courseId) {
        return assignmentRepository.findByCourseIdOrderByCreatedAtDesc(courseId);
    }

    /**
     * Получить предстоящие задания курса
     */
    public List<Assignment> getUpcomingAssignments(Long courseId) {
        return assignmentRepository.findUpcomingAssignmentsByCourseId(courseId, LocalDateTime.now());
    }

    /**
     * Получить все задания
     */
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    /**
     * Обновить задание
     */
    @Transactional
    public Assignment updateAssignment(Long id, AssignmentRequest request) {
        Assignment assignment = getAssignmentById(id);

        assignment.setTitleEn(request.getTitleEn());
        assignment.setTitleRu(request.getTitleRu());
        assignment.setTitleKk(request.getTitleKk());
        assignment.setDescriptionEn(request.getDescriptionEn());
        assignment.setDescriptionRu(request.getDescriptionRu());
        assignment.setDescriptionKk(request.getDescriptionKk());
        assignment.setMaxScore(request.getMaxScore());
        assignment.setDueDate(request.getDueDate());

        return assignmentRepository.save(assignment);
    }

    /**
     * Удалить задание
     */
    @Transactional
    public void deleteAssignment(Long id) {
        Assignment assignment = getAssignmentById(id);
        assignmentRepository.delete(assignment);
    }
}
