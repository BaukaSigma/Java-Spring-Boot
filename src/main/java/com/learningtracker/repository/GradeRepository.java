package com.learningtracker.repository;

import com.learningtracker.model.Assignment;
import com.learningtracker.model.Enrollment;
import com.learningtracker.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с оценками
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    /**
     * Найти все оценки по записи на курс
     */
    List<Grade> findByEnrollment(Enrollment enrollment);

    /**
     * Найти все оценки студента по ID записи
     */
    List<Grade> findByEnrollmentId(Long enrollmentId);

    /**
     * Найти все оценки за конкретное задание
     */
    List<Grade> findByAssignment(Assignment assignment);

    /**
     * Найти все оценки за задание по ID
     */
    List<Grade> findByAssignmentId(Long assignmentId);

    /**
     * Найти оценку студента за конкретное задание
     */
    Optional<Grade> findByEnrollmentAndAssignment(Enrollment enrollment, Assignment assignment);

    /**
     * Найти оценку по ID записи и ID задания
     */
    Optional<Grade> findByEnrollmentIdAndAssignmentId(Long enrollmentId, Long assignmentId);

    /**
     * Проверить, существует ли оценка за задание
     */
    boolean existsByEnrollmentIdAndAssignmentId(Long enrollmentId, Long assignmentId);

    /**
     * Найти все оценки студента по ID студента
     */
    @Query("SELECT g FROM Grade g WHERE g.enrollment.student.id = :studentId")
    List<Grade> findByStudentId(Long studentId);

    /**
     * Найти все оценки курса
     */
    @Query("SELECT g FROM Grade g WHERE g.enrollment.course.id = :courseId")
    List<Grade> findByCourseId(Long courseId);
}
