package com.learningtracker.repository;

import com.learningtracker.model.Assignment;
import com.learningtracker.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Репозиторий для работы с заданиями
 */
@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    /**
     * Найти все задания курса
     */
    List<Assignment> findByCourse(Course course);

    /**
     * Найти все задания курса по ID
     */
    List<Assignment> findByCourseId(Long courseId);

    /**
     * Найти задания с дедлайном до указанной даты
     */
    List<Assignment> findByDueDateBefore(LocalDateTime date);

    /**
     * Найти задания с дедлайном после указанной даты
     */
    List<Assignment> findByDueDateAfter(LocalDateTime date);

    /**
     * Найти задания курса, отсортированные по дате создания
     */
    List<Assignment> findByCourseIdOrderByCreatedAtDesc(Long courseId);

    /**
     * Найти предстоящие задания курса
     */
    @Query("SELECT a FROM Assignment a WHERE a.course.id = :courseId AND a.dueDate > :now ORDER BY a.dueDate ASC")
    List<Assignment> findUpcomingAssignmentsByCourseId(Long courseId, LocalDateTime now);
}
