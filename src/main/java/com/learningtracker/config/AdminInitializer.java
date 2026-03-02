package com.learningtracker.config;

import com.learningtracker.model.User;
import com.learningtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.JdbcTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * Инициализатор, который проверяет наличие дефолтного администратора при запуске приложения.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        migrateDatabaseSchema();

        // Создаем дефолтного админа: username "BAUKA", пароль "12345678", если его нет
        if (!userRepository.existsByUsername("BAUKA")) {
            User defaultAdmin = new User();
            defaultAdmin.setUsername("BAUKA");
            defaultAdmin.setEmail("admin@bauka.kz");
            defaultAdmin.setPassword(passwordEncoder.encode("12345678"));
            defaultAdmin.setFirstName("Super");
            defaultAdmin.setLastName("Admin");
            defaultAdmin.setRole(User.Role.ADMIN);

            userRepository.save(defaultAdmin);
            log.info("Default admin user 'BAUKA' created successfully.");
        }
    }

    private void migrateDatabaseSchema() {
        try {
            // Пытаемся переименовать столбцы в таблицах courses и assignments
            log.info("Checking database schema for required column renames...");
            try {
                jdbcTemplate.execute("ALTER TABLE courses RENAME COLUMN title_ru TO title");
                log.info("Renamed courses.title_ru to title");
            } catch (Exception e) {}
            try {
                jdbcTemplate.execute("ALTER TABLE courses RENAME COLUMN description_ru TO description");
                log.info("Renamed courses.description_ru to description");
            } catch (Exception e) {}
            try {
                jdbcTemplate.execute("ALTER TABLE assignments RENAME COLUMN title_ru TO title");
                log.info("Renamed assignments.title_ru to title");
            } catch (Exception e) {}
            try {
                jdbcTemplate.execute("ALTER TABLE assignments RENAME COLUMN description_ru TO description");
                log.info("Renamed assignments.description_ru to description");
            } catch (Exception e) {}
            
            // Удаляем старые колонки, которые имеют ограничения NOT NULL
            try {
                jdbcTemplate.execute("ALTER TABLE courses " +
                        "DROP COLUMN IF EXISTS course_code, " +
                        "DROP COLUMN IF EXISTS title_en, " +
                        "DROP COLUMN IF EXISTS title_kk, " +
                        "DROP COLUMN IF EXISTS description_en, " +
                        "DROP COLUMN IF EXISTS description_kk");
                log.info("Dropped legacy columns from courses table");
            } catch (Exception e) {}

            try {
                jdbcTemplate.execute("ALTER TABLE assignments " +
                        "DROP COLUMN IF EXISTS title_en, " +
                        "DROP COLUMN IF EXISTS title_kk, " +
                        "DROP COLUMN IF EXISTS description_en, " +
                        "DROP COLUMN IF EXISTS description_kk");
                log.info("Dropped legacy columns from assignments table");
            } catch (Exception e) {}
            
        } catch (Exception e) {
            log.warn("Error during automatic schema migration.", e);
        }
    }
}
