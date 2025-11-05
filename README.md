# 🎓 Learning Progress Tracker

Веб-приложение для эффективного отслеживания учебного прогресса студентов с поддержкой трех языков (русский, казахский, английский).

## 📋 Описание

Learning Progress Tracker - это полнофункциональное веб-приложение на Spring Boot для управления учебным процессом. Система позволяет:

- ✅ Управлять курсами и заданиями
- ✅ Отслеживать оценки студентов
- ✅ Контролировать посещаемость
- ✅ Работать с системой ролей (Студент, Преподаватель, Администратор)
- ✅ Использовать JWT аутентификацию
- ✅ Поддерживать три языка интерфейса

## 🛠️ Технологии

### Backend
- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Security** (JWT аутентификация)
- **Spring Data JPA** (Hibernate)
- **PostgreSQL** (база данных)
- **Lombok** (упрощение кода)

### Frontend
- **Thymeleaf** (серверный рендеринг)
- **Bootstrap 5** (стилизация)
- **Bootstrap Icons**

## 📦 Установка (ДЛЯ НОВИЧКОВ)

### Шаг 1: Установка Java JDK 17

1. Перейдите на сайт: https://www.oracle.com/java/technologies/downloads/
2. Скачайте Java JDK 17 для вашей операционной системы
3. Установите JDK, следуя инструкциям установщика
4. Проверьте установку, открыв командную строку и введя:
```bash
java -version
```
Должно вывести что-то вроде: `java version "17.x.x"`

### Шаг 2: Установка PostgreSQL

1. Перейдите на сайт: https://www.postgresql.org/download/
2. Скачайте PostgreSQL для вашей ОС
3. Установите PostgreSQL (**ВАЖНО: запомните пароль для пользователя postgres!**)
4. По умолчанию PostgreSQL запускается на порту **5432**

### Шаг 3: Создание базы данных

1. Откройте **pgAdmin 4** (устанавливается вместе с PostgreSQL)
2. Введите мастер-пароль, который вы установили
3. Найдите в левой панели **PostgreSQL 15** (или вашу версию)
4. Нажмите правой кнопкой на **Databases** → **Create** → **Database**
5. Введите имя базы данных: `learning_progress_tracker`
6. Нажмите **Save**

### Шаг 4: Установка IntelliJ IDEA (опционально)

1. Перейдите на сайт: https://www.jetbrains.com/idea/download/
2. Скачайте **Community Edition** (бесплатная версия)
3. Установите IntelliJ IDEA

### Шаг 5: Настройка проекта

1. Откройте файл `src/main/resources/application.properties`
2. Найдите строки с настройками базы данных:
```properties
spring.datasource.username=postgres
spring.datasource.password=your_password_here
```
3. **ВАЖНО:** Замените `your_password_here` на ваш пароль от PostgreSQL!

4. Также замените JWT секретный ключ:
```properties
jwt.secret=MyVerySecretKeyForJWTTokenGeneration2024LearningProgressTracker12345
```
Замените на свой уникальный ключ (минимум 32 символа)

## 🚀 Запуск приложения

### Способ 1: Через IntelliJ IDEA

1. Откройте проект в IntelliJ IDEA (File → Open → выберите папку проекта)
2. Подождите, пока Maven загрузит все зависимости
3. Найдите файл `LearningProgressTrackerApplication.java`
4. Нажмите на зеленую стрелку слева от класса → Run
5. Приложение запустится на http://localhost:8080

### Способ 2: Через командную строку

```bash
# Windows
mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

### Способ 3: Через JAR файл

```bash
# Сначала собрать проект
mvnw.cmd clean package

# Затем запустить
java -jar target/learning-progress-tracker-1.0.0.jar
```

## 🌐 Доступ к приложению

После запуска приложение доступно по адресам:

- **Веб-интерфейс:** http://localhost:8080
- **API:** http://localhost:8080/api

## 📚 API Эндпоинты

### Аутентификация (`/api/auth`)

| Метод | URL | Описание |
|-------|-----|----------|
| POST | `/api/auth/register` | Регистрация нового пользователя |
| POST | `/api/auth/login` | Вход в систему (получение JWT токена) |
| GET | `/api/auth/me` | Информация о текущем пользователе |

### Курсы (`/api/courses`)

| Метод | URL | Описание | Права доступа |
|-------|-----|----------|---------------|
| GET | `/api/courses` | Список всех курсов | Все |
| GET | `/api/courses/{id}` | Получить курс по ID | Все |
| POST | `/api/courses` | Создать курс | INSTRUCTOR, ADMIN |
| PUT | `/api/courses/{id}` | Обновить курс | INSTRUCTOR, ADMIN |
| DELETE | `/api/courses/{id}` | Удалить курс | ADMIN |

### Записи на курсы (`/api/enrollments`)

| Метод | URL | Описание |
|-------|-----|----------|
| POST | `/api/enrollments` | Записать студента на курс |
| GET | `/api/enrollments/student/{id}` | Записи студента |
| GET | `/api/enrollments/course/{id}` | Студенты курса |
| DELETE | `/api/enrollments/{id}/drop` | Отписаться от курса |

### Задания (`/api/assignments`)

| Метод | URL | Описание | Права доступа |
|-------|-----|----------|---------------|
| GET | `/api/assignments/course/{id}` | Задания курса | Все |
| POST | `/api/assignments` | Создать задание | INSTRUCTOR, ADMIN |
| PUT | `/api/assignments/{id}` | Обновить задание | INSTRUCTOR, ADMIN |
| DELETE | `/api/assignments/{id}` | Удалить задание | INSTRUCTOR, ADMIN |

### Оценки (`/api/grades`)

| Метод | URL | Описание | Права доступа |
|-------|-----|----------|---------------|
| GET | `/api/grades/student/{id}` | Оценки студента | Все |
| POST | `/api/grades` | Выставить оценку | INSTRUCTOR, ADMIN |
| PUT | `/api/grades/{id}` | Обновить оценку | INSTRUCTOR, ADMIN |

### Посещаемость (`/api/attendance`)

| Метод | URL | Описание | Права доступа |
|-------|-----|----------|---------------|
| GET | `/api/attendance/student/{id}` | Посещаемость студента | Все |
| POST | `/api/attendance` | Отметить посещаемость | INSTRUCTOR, ADMIN |
| PUT | `/api/attendance/{id}` | Обновить посещаемость | INSTRUCTOR, ADMIN |

## 🧪 Примеры API запросов

### 1. Регистрация пользователя

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "student1",
    "email": "student1@example.com",
    "password": "password123",
    "firstName": "Иван",
    "lastName": "Иванов",
    "role": "STUDENT"
  }'
```

### 2. Вход в систему

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "student1",
    "password": "password123"
  }'
```

Ответ:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "id": 1,
  "username": "student1",
  "email": "student1@example.com",
  "role": "STUDENT"
}
```

### 3. Создание курса (с токеном)

```bash
curl -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE" \
  -d '{
    "courseCode": "CS101",
    "titleEn": "Introduction to Computer Science",
    "titleRu": "Введение в информатику",
    "titleKk": "Информатикаға кіріспе",
    "descriptionEn": "Basic concepts of CS",
    "descriptionRu": "Основные концепции информатики",
    "descriptionKk": "Информатиканың негізгі ұғымдары",
    "credits": 3
  }'
```

## 🔐 Роли пользователей

### STUDENT (Студент)
- Просмотр своих курсов
- Просмотр своих оценок
- Просмотр своей посещаемости
- Просмотр заданий

### INSTRUCTOR (Преподаватель)
- Все права студента +
- Создание и управление курсами
- Создание заданий
- Выставление оценок
- Отметка посещаемости

### ADMIN (Администратор)
- Все права преподавателя +
- Удаление курсов
- Управление пользователями
- Доступ ко всей статистике

## 🌍 Многоязычность

Приложение поддерживает три языка:
- 🇬🇧 Английский (en)
- 🇷🇺 Русский (ru)
- 🇰🇿 Казахский (kk)

Переводы находятся в файлах:
- `src/main/resources/messages_en.properties`
- `src/main/resources/messages_ru.properties`
- `src/main/resources/messages_kk.properties`

## 🗂️ Структура проекта

```
learning-progress-tracker/
├── src/
│   ├── main/
│   │   ├── java/com/learningtracker/
│   │   │   ├── config/              # Конфигурация (Security, CORS)
│   │   │   ├── controller/          # REST контроллеры
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   │   ├── request/         # DTO для запросов
│   │   │   │   └── response/        # DTO для ответов
│   │   │   ├── exception/           # Обработка исключений
│   │   │   ├── model/               # Entity классы (модели БД)
│   │   │   ├── repository/          # JPA репозитории
│   │   │   ├── security/            # JWT фильтр и UserDetails
│   │   │   ├── service/             # Бизнес-логика
│   │   │   └── LearningProgressTrackerApplication.java
│   │   └── resources/
│   │       ├── application.properties  # Конфигурация приложения
│   │       ├── messages_*.properties   # Файлы переводов
│   │       └── templates/              # HTML страницы
│   └── test/                        # Тесты
├── pom.xml                          # Maven зависимости
└── README.md                        # Эта инструкция
```

## ❗ Частые ошибки и решения

### 1. "Cannot connect to database"
**Проблема:** Приложение не может подключиться к PostgreSQL

**Решение:**
- Проверьте, что PostgreSQL запущен
- Проверьте правильность пароля в `application.properties`
- Убедитесь, что база данных `learning_progress_tracker` создана
- Проверьте порт (должен быть 5432)

### 2. "Table doesn't exist"
**Проблема:** Таблицы не созданы в базе данных

**Решение:**
- Убедитесь, что в `application.properties` установлено:
```properties
spring.jpa.hibernate.ddl-auto=update
```
- При первом запуске можно установить `create`, затем вернуть `update`

### 3. "JWT dependencies not found"
**Проблема:** Maven не может найти JWT библиотеки

**Решение:**
- Откройте проект в IntelliJ IDEA
- Нажмите **Maven** справа → кнопка обновления (круговая стрелка)
- Или в командной строке: `mvnw clean install`

### 4. "Port 8080 already in use"
**Проблема:** Порт 8080 уже занят

**Решение:**
- Измените порт в `application.properties`:
```properties
server.port=8081
```

### 5. "Access Denied / 403 Error"
**Проблема:** Нет прав доступа к эндпоинту

**Решение:**
- Убедитесь, что вы отправляете JWT токен в заголовке:
```
Authorization: Bearer YOUR_TOKEN_HERE
```
- Проверьте, что у вашего пользователя есть нужная роль

## 🧪 Тестирование через Postman

1. Скачайте Postman: https://www.postman.com/downloads/
2. Создайте новый запрос
3. Установите метод (GET, POST, PUT, DELETE)
4. Введите URL: `http://localhost:8080/api/...`
5. Для защищенных эндпоинтов:
   - Перейдите в **Headers**
   - Добавьте ключ: `Authorization`
   - Значение: `Bearer YOUR_JWT_TOKEN`

## 📊 База данных

### Схема базы данных

```
users (пользователи)
  ├── id
  ├── username
  ├── email
  ├── password
  ├── first_name
  ├── last_name
  ├── role
  └── created_at

courses (курсы)
  ├── id
  ├── course_code
  ├── title_en/ru/kk
  ├── description_en/ru/kk
  ├── credits
  ├── instructor_id → users.id
  └── created_at

enrollments (записи на курсы)
  ├── id
  ├── student_id → users.id
  ├── course_id → courses.id
  ├── enrollment_date
  └── status

assignments (задания)
  ├── id
  ├── course_id → courses.id
  ├── title_en/ru/kk
  ├── description_en/ru/kk
  ├── max_score
  ├── due_date
  └── created_at

grades (оценки)
  ├── id
  ├── enrollment_id → enrollments.id
  ├── assignment_id → assignments.id
  ├── score
  ├── submitted_at
  ├── graded_at
  └── feedback_en/ru/kk

attendance (посещаемость)
  ├── id
  ├── enrollment_id → enrollments.id
  ├── attendance_date
  ├── status (PRESENT/ABSENT/LATE/EXCUSED)
  └── notes
```

## 🔧 Разработка

### Добавление новых функций

1. Создайте Entity в `model/`
2. Создайте Repository в `repository/`
3. Создайте Service в `service/`
4. Создайте Controller в `controller/`
5. Добавьте DTO в `dto/request` и `dto/response`

### Запуск тестов

```bash
mvnw test
```

## 📝 Лицензия

Этот проект создан в образовательных целях.

## 👨‍💻 Автор

Создано для изучения Spring Boot и разработки веб-приложений.

## 🆘 Поддержка

Если у вас возникли вопросы:
1. Проверьте раздел "Частые ошибки"
2. Просмотрите логи приложения в консоли
3. Проверьте настройки в `application.properties`

## 🎯 Следующие шаги

После успешного запуска:
1. Зарегистрируйте тестового пользователя
2. Создайте курс (как INSTRUCTOR или ADMIN)
3. Запишите студента на курс
4. Создайте задание
5. Выставьте оценку
6. Отметьте посещаемость

Удачи в изучении! 🚀
