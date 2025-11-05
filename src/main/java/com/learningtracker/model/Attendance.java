package com.learningtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Модель посещаемости студента
 * Отслеживает присутствие студента на занятиях
 */
@Entity
@Table(name = "attendance", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"enrollment_id", "attendance_date"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Запись студента на курс
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    /**
     * Дата занятия
     */
    @Column(nullable = false)
    @NotNull(message = "Attendance date is required")
    private LocalDate attendanceDate;

    /**
     * Статус посещаемости
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    @NotNull(message = "Attendance status is required")
    private AttendanceStatus status;

    /**
     * Дополнительные заметки (например, причина отсутствия)
     */
    @Column(columnDefinition = "TEXT")
    private String notes;

    /**
     * Перечисление статусов посещаемости
     */
    public enum AttendanceStatus {
        PRESENT,    // Присутствовал
        ABSENT,     // Отсутствовал
        LATE,       // Опоздал
        EXCUSED     // Отсутствовал по уважительной причине
    }
}
