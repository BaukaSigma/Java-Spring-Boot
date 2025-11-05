package com.learningtracker.repository;

import com.learningtracker.model.Attendance;
import com.learningtracker.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с посещаемостью
 */
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    /**
     * Найти все записи посещаемости по записи на курс
     */
    List<Attendance> findByEnrollment(Enrollment enrollment);

    /**
     * Найти все записи посещаемости по ID записи
     */
    List<Attendance> findByEnrollmentId(Long enrollmentId);

    /**
     * Найти запись посещаемости на конкретную дату
     */
    Optional<Attendance> findByEnrollmentAndAttendanceDate(Enrollment enrollment, LocalDate date);

    /**
     * Найти записи посещаемости в диапазоне дат
     */
    List<Attendance> findByEnrollmentAndAttendanceDateBetween(
        Enrollment enrollment, LocalDate startDate, LocalDate endDate
    );

    /**
     * Найти все записи посещаемости курса
     */
    @Query("SELECT a FROM Attendance a WHERE a.enrollment.course.id = :courseId")
    List<Attendance> findByCourseId(Long courseId);

    /**
     * Найти все записи посещаемости студента
     */
    @Query("SELECT a FROM Attendance a WHERE a.enrollment.student.id = :studentId")
    List<Attendance> findByStudentId(Long studentId);

    /**
     * Найти записи посещаемости курса на конкретную дату
     */
    @Query("SELECT a FROM Attendance a WHERE a.enrollment.course.id = :courseId AND a.attendanceDate = :date")
    List<Attendance> findByCourseIdAndDate(Long courseId, LocalDate date);

    /**
     * Проверить, существует ли запись посещаемости
     */
    boolean existsByEnrollmentIdAndAttendanceDate(Long enrollmentId, LocalDate date);
}
