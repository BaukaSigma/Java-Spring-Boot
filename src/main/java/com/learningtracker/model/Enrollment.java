package com.learningtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Модель записи студента на курс
 * Связывает студента с курсом
 */
@Entity
@Table(name = "enrollments", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "course_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Студент, записанный на курс
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    /**
     * Курс, на который записан студент
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    /**
     * Дата записи на курс
     */
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime enrollmentDate;

    /**
     * Статус записи (ACTIVE, COMPLETED, DROPPED)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EnrollmentStatus status = EnrollmentStatus.ACTIVE;

    /**
     * Оценки студента по этому курсу
     */
    @JsonIgnore
    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Grade> grades = new HashSet<>();

    /**
     * Записи посещаемости студента
     */
    @JsonIgnore
    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Attendance> attendanceRecords = new HashSet<>();

    /**
     * Перечисление статусов записи
     */
    public enum EnrollmentStatus {
        ACTIVE,      // Активная запись
        COMPLETED,   // Курс завершен
        DROPPED      // Студент отписался от курса
    }

    /**
     * Вычислить средний балл студента по этому курсу
     */
    public Double getAverageGrade() {
        if (grades == null || grades.isEmpty()) {
            return null;
        }
        return grades.stream()
            .filter(g -> g.getScore() != null)
            .mapToDouble(g -> g.getScore().doubleValue())
            .average()
            .orElse(0.0);
    }

    /**
     * Вычислить процент посещаемости
     */
    public Double getAttendancePercentage() {
        if (attendanceRecords == null || attendanceRecords.isEmpty()) {
            return null;
        }
        long totalRecords = attendanceRecords.size();
        long presentRecords = attendanceRecords.stream()
            .filter(a -> a.getStatus() == Attendance.AttendanceStatus.PRESENT)
            .count();
        return (presentRecords * 100.0) / totalRecords;
    }
}
