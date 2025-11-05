package com.learningtracker.repository;

import com.learningtracker.model.Course;
import com.learningtracker.model.Enrollment;
import com.learningtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с записями на курсы
 */
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    /**
     * Найти все записи студента
     */
    List<Enrollment> findByStudent(User student);

    /**
     * Найти все записи студента по ID
     */
    List<Enrollment> findByStudentId(Long studentId);

    /**
     * Найти все записи на конкретный курс
     */
    List<Enrollment> findByCourse(Course course);

    /**
     * Найти все записи на курс по ID курса
     */
    List<Enrollment> findByCourseId(Long courseId);

    /**
     * Найти запись студента на конкретный курс
     */
    Optional<Enrollment> findByStudentAndCourse(User student, Course course);

    /**
     * Найти запись по ID студента и ID курса
     */
    Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);

    /**
     * Найти активные записи студента
     */
    List<Enrollment> findByStudentAndStatus(User student, Enrollment.EnrollmentStatus status);

    /**
     * Проверить, записан ли студент на курс
     */
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
}
