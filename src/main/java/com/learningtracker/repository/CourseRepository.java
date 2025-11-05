package com.learningtracker.repository;

import com.learningtracker.model.Course;
import com.learningtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с курсами
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Найти курс по коду курса
     */
    Optional<Course> findByCourseCode(String courseCode);

    /**
     * Проверить существование курса с таким кодом
     */
    boolean existsByCourseCode(String courseCode);

    /**
     * Найти все курсы конкретного преподавателя
     */
    List<Course> findByInstructor(User instructor);

    /**
     * Найти все курсы по ID преподавателя
     */
    List<Course> findByInstructorId(Long instructorId);

    /**
     * Поиск курсов по названию (на любом языке)
     */
    List<Course> findByTitleEnContainingIgnoreCaseOrTitleRuContainingIgnoreCaseOrTitleKkContainingIgnoreCase(
        String titleEn, String titleRu, String titleKk
    );
}
