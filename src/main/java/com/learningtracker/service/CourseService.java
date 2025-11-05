package com.learningtracker.service;

import com.learningtracker.dto.request.CourseRequest;
import com.learningtracker.model.Course;
import com.learningtracker.model.User;
import com.learningtracker.repository.CourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для работы с курсами
 * Управление созданием, обновлением и поиском курсов
 */
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;
    
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Создать новый курс
     */
    @Transactional
    public Course createCourse(CourseRequest request) {
        // Проверка на существование курса с таким кодом
        if (courseRepository.existsByCourseCode(request.getCourseCode())) {
            throw new RuntimeException("Course with code " + request.getCourseCode() + " already exists");
        }

        Course course = new Course();
        course.setCourseCode(request.getCourseCode());
        course.setTitleEn(request.getTitleEn());
        course.setTitleRu(request.getTitleRu());
        course.setTitleKk(request.getTitleKk());
        course.setDescriptionEn(request.getDescriptionEn());
        course.setDescriptionRu(request.getDescriptionRu());
        course.setDescriptionKk(request.getDescriptionKk());
        course.setCredits(request.getCredits());

        // Назначение преподавателя
        if (request.getInstructorId() != null) {
            User instructor = userService.getUserById(request.getInstructorId());
            if (instructor.getRole() != User.Role.INSTRUCTOR && instructor.getRole() != User.Role.ADMIN) {
                throw new RuntimeException("User is not an instructor");
            }
            course.setInstructor(instructor);
        }

        return courseRepository.save(course);
    }

    /**
     * Получить курс по ID
     */
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    /**
     * Получить курс по коду
     */
    public Course getCourseByCourseCode(String courseCode) {
        return courseRepository.findByCourseCode(courseCode)
                .orElseThrow(() -> new RuntimeException("Course not found with code: " + courseCode));
    }

    /**
     * Получить все курсы
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /**
     * Получить курсы преподавателя
     */
    public List<Course> getCoursesByInstructor(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    /**
     * Обновить курс
     */
    @Transactional
    public Course updateCourse(Long id, CourseRequest request) {
        Course course = getCourseById(id);

        // Проверка кода курса на уникальность при изменении
        if (!course.getCourseCode().equals(request.getCourseCode())) {
            if (courseRepository.existsByCourseCode(request.getCourseCode())) {
                throw new RuntimeException("Course code already exists");
            }
            course.setCourseCode(request.getCourseCode());
        }

        course.setTitleEn(request.getTitleEn());
        course.setTitleRu(request.getTitleRu());
        course.setTitleKk(request.getTitleKk());
        course.setDescriptionEn(request.getDescriptionEn());
        course.setDescriptionRu(request.getDescriptionRu());
        course.setDescriptionKk(request.getDescriptionKk());
        course.setCredits(request.getCredits());

        if (request.getInstructorId() != null) {
            User instructor = userService.getUserById(request.getInstructorId());
            if (instructor.getRole() != User.Role.INSTRUCTOR && instructor.getRole() != User.Role.ADMIN) {
                throw new RuntimeException("User is not an instructor");
            }
            course.setInstructor(instructor);
        }

        return courseRepository.save(course);
    }

    /**
     * Удалить курс
     */
    @Transactional
    public void deleteCourse(Long id) {
        // Проверяем существование курса
        Course course = getCourseById(id);
        
        // Удаляем все связанные данные через нативные SQL запросы
        // 1. Удаляем attendance через enrollments
        entityManager.createNativeQuery(
            "DELETE FROM attendance WHERE enrollment_id IN (SELECT id FROM enrollments WHERE course_id = :courseId)")
            .setParameter("courseId", id)
            .executeUpdate();
        
        // 2. Удаляем grades через enrollments
        entityManager.createNativeQuery(
            "DELETE FROM grades WHERE enrollment_id IN (SELECT id FROM enrollments WHERE course_id = :courseId)")
            .setParameter("courseId", id)
            .executeUpdate();
        
        // 3. Удаляем grades через assignments
        entityManager.createNativeQuery(
            "DELETE FROM grades WHERE assignment_id IN (SELECT id FROM assignments WHERE course_id = :courseId)")
            .setParameter("courseId", id)
            .executeUpdate();
        
        // 4. Удаляем enrollments
        entityManager.createNativeQuery(
            "DELETE FROM enrollments WHERE course_id = :courseId")
            .setParameter("courseId", id)
            .executeUpdate();
        
        // 5. Удаляем assignments
        entityManager.createNativeQuery(
            "DELETE FROM assignments WHERE course_id = :courseId")
            .setParameter("courseId", id)
            .executeUpdate();
        
        // 6. Удаляем сам курс
        entityManager.createNativeQuery(
            "DELETE FROM courses WHERE id = :courseId")
            .setParameter("courseId", id)
            .executeUpdate();
    }

    /**
     * Поиск курсов по названию
     */
    public List<Course> searchCourses(String query) {
        return courseRepository.findByTitleEnContainingIgnoreCaseOrTitleRuContainingIgnoreCaseOrTitleKkContainingIgnoreCase(
                query, query, query
        );
    }
}
