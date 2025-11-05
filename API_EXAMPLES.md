# 📡 API Examples - Примеры запросов

Полное руководство по использованию API Learning Progress Tracker.

## 🔐 1. АУТЕНТИФИКАЦИЯ

### Регистрация студента

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "ivan_petrov",
    "email": "ivan@example.com",
    "password": "securePass123",
    "firstName": "Иван",
    "lastName": "Петров",
    "role": "STUDENT"
  }'
```

**Ответ:**
```json
{
  "message": "User registered successfully! Username: ivan_petrov",
  "success": true
}
```

### Регистрация преподавателя

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "prof_smith",
    "email": "smith@example.com",
    "password": "profPass123",
    "firstName": "John",
    "lastName": "Smith",
    "role": "INSTRUCTOR"
  }'
```

### Вход в систему

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "ivan_petrov",
    "password": "securePass123"
  }'
```

**Ответ:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJpdmFuX3BldHJvdiIsImlhdCI6MTYzMjQ4...",
  "type": "Bearer",
  "id": 1,
  "username": "ivan_petrov",
  "email": "ivan@example.com",
  "role": "STUDENT"
}
```

**ВАЖНО:** Сохраните токен! Он понадобится для всех последующих запросов.

### Получить информацию о текущем пользователе

```bash
curl -X GET http://localhost:8080/api/auth/me \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 📚 2. КУРСЫ

### Получить все курсы

```bash
curl -X GET http://localhost:8080/api/courses
```

### Получить курс по ID

```bash
curl -X GET http://localhost:8080/api/courses/1
```

### Создать курс (требуется роль INSTRUCTOR или ADMIN)

```bash
curl -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "courseCode": "CS101",
    "titleEn": "Introduction to Computer Science",
    "titleRu": "Введение в информатику",
    "titleKk": "Информатикаға кіріспе",
    "descriptionEn": "Learn the fundamentals of computer science",
    "descriptionRu": "Изучите основы информатики",
    "descriptionKk": "Информатиканың негіздерін үйреніңіз",
    "credits": 3,
    "instructorId": 2
  }'
```

### Создать курс по математике

```bash
curl -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "courseCode": "MATH201",
    "titleEn": "Calculus I",
    "titleRu": "Математический анализ I",
    "titleKk": "Математикалық талдау I",
    "descriptionEn": "Introduction to differential and integral calculus",
    "descriptionRu": "Введение в дифференциальное и интегральное исчисление",
    "descriptionKk": "Дифференциалдық және интегралдық есептеуге кіріспе",
    "credits": 4,
    "instructorId": 2
  }'
```

### Обновить курс

```bash
curl -X PUT http://localhost:8080/api/courses/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "courseCode": "CS101",
    "titleEn": "Advanced Computer Science",
    "titleRu": "Продвинутая информатика",
    "titleKk": "Жетілдірілген информатика",
    "descriptionEn": "Advanced topics in CS",
    "descriptionRu": "Продвинутые темы в информатике",
    "descriptionKk": "Информатикадағы жетілдірілген тақырыптар",
    "credits": 4,
    "instructorId": 2
  }'
```

### Поиск курсов

```bash
curl -X GET "http://localhost:8080/api/courses/search?query=Computer"
```

### Получить курсы преподавателя

```bash
curl -X GET http://localhost:8080/api/courses/instructor/2
```

### Удалить курс (только ADMIN)

```bash
curl -X DELETE http://localhost:8080/api/courses/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 🎓 3. ЗАПИСИ НА КУРСЫ

### Записать студента на курс

```bash
curl -X POST "http://localhost:8080/api/enrollments?studentId=1&courseId=1" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Получить записи студента

```bash
curl -X GET http://localhost:8080/api/enrollments/student/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Получить активные записи студента

```bash
curl -X GET http://localhost:8080/api/enrollments/student/1/active \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Получить студентов курса

```bash
curl -X GET http://localhost:8080/api/enrollments/course/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Изменить статус записи

```bash
curl -X PUT "http://localhost:8080/api/enrollments/1/status?status=COMPLETED" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

Возможные статусы: `ACTIVE`, `COMPLETED`, `DROPPED`

### Отписаться от курса

```bash
curl -X DELETE http://localhost:8080/api/enrollments/1/drop \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 📝 4. ЗАДАНИЯ

### Получить задания курса

```bash
curl -X GET http://localhost:8080/api/assignments/course/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Получить предстоящие задания

```bash
curl -X GET http://localhost:8080/api/assignments/course/1/upcoming \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Создать задание (INSTRUCTOR или ADMIN)

```bash
curl -X POST http://localhost:8080/api/assignments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "courseId": 1,
    "titleEn": "Homework 1",
    "titleRu": "Домашнее задание 1",
    "titleKk": "Үй тапсырмасы 1",
    "descriptionEn": "Complete exercises 1-10",
    "descriptionRu": "Выполните упражнения 1-10",
    "descriptionKk": "1-10 жаттығуларды орындаңыз",
    "maxScore": 100,
    "dueDate": "2024-12-31T23:59:59"
  }'
```

### Создать задание по программированию

```bash
curl -X POST http://localhost:8080/api/assignments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "courseId": 1,
    "titleEn": "Programming Project",
    "titleRu": "Программный проект",
    "titleKk": "Бағдарламалау жобасы",
    "descriptionEn": "Create a simple web application",
    "descriptionRu": "Создайте простое веб-приложение",
    "descriptionKk": "Қарапайым веб-қосымша жасаңыз",
    "maxScore": 100,
    "dueDate": "2025-01-15T23:59:59"
  }'
```

### Обновить задание

```bash
curl -X PUT http://localhost:8080/api/assignments/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "courseId": 1,
    "titleEn": "Homework 1 (Updated)",
    "titleRu": "Домашнее задание 1 (Обновлено)",
    "titleKk": "Үй тапсырмасы 1 (Жаңартылған)",
    "descriptionEn": "Complete exercises 1-15",
    "descriptionRu": "Выполните упражнения 1-15",
    "descriptionKk": "1-15 жаттығуларды орындаңыз",
    "maxScore": 100,
    "dueDate": "2024-12-31T23:59:59"
  }'
```

### Удалить задание

```bash
curl -X DELETE http://localhost:8080/api/assignments/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 🎯 5. ОЦЕНКИ

### Получить оценки студента

```bash
curl -X GET http://localhost:8080/api/grades/student/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Получить оценки по записи на курс

```bash
curl -X GET http://localhost:8080/api/grades/enrollment/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Получить оценки за задание (INSTRUCTOR или ADMIN)

```bash
curl -X GET http://localhost:8080/api/grades/assignment/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Выставить оценку (INSTRUCTOR или ADMIN)

```bash
curl -X POST http://localhost:8080/api/grades \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "enrollmentId": 1,
    "assignmentId": 1,
    "score": 85.5,
    "submittedAt": "2024-11-01T14:30:00",
    "feedbackEn": "Good work! Could improve on problem 5.",
    "feedbackRu": "Хорошая работа! Можно улучшить задачу 5.",
    "feedbackKk": "Жақсы жұмыс! 5-есепті жақсартуға болады."
  }'
```

### Выставить оценку с минимальной информацией

```bash
curl -X POST http://localhost:8080/api/grades \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "enrollmentId": 1,
    "assignmentId": 2,
    "score": 92
  }'
```

### Обновить оценку

```bash
curl -X PUT http://localhost:8080/api/grades/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "enrollmentId": 1,
    "assignmentId": 1,
    "score": 90,
    "feedbackEn": "Excellent work!",
    "feedbackRu": "Отличная работа!",
    "feedbackKk": "Керемет жұмыс!"
  }'
```

---

## 📅 6. ПОСЕЩАЕМОСТЬ

### Получить посещаемость студента

```bash
curl -X GET http://localhost:8080/api/attendance/student/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Получить посещаемость по записи

```bash
curl -X GET http://localhost:8080/api/attendance/enrollment/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Получить посещаемость курса (INSTRUCTOR или ADMIN)

```bash
curl -X GET http://localhost:8080/api/attendance/course/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Получить посещаемость курса на дату

```bash
curl -X GET "http://localhost:8080/api/attendance/course/1/date?date=2024-11-03" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Отметить посещаемость (INSTRUCTOR или ADMIN)

```bash
curl -X POST http://localhost:8080/api/attendance \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "enrollmentId": 1,
    "attendanceDate": "2024-11-03",
    "status": "PRESENT",
    "notes": "On time"
  }'
```

Возможные статусы: `PRESENT`, `ABSENT`, `LATE`, `EXCUSED`

### Отметить опоздание

```bash
curl -X POST http://localhost:8080/api/attendance \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "enrollmentId": 1,
    "attendanceDate": "2024-11-04",
    "status": "LATE",
    "notes": "Arrived 15 minutes late"
  }'
```

### Отметить отсутствие по уважительной причине

```bash
curl -X POST http://localhost:8080/api/attendance \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "enrollmentId": 1,
    "attendanceDate": "2024-11-05",
    "status": "EXCUSED",
    "notes": "Medical certificate provided"
  }'
```

### Обновить посещаемость

```bash
curl -X PUT http://localhost:8080/api/attendance/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "enrollmentId": 1,
    "attendanceDate": "2024-11-03",
    "status": "PRESENT",
    "notes": "Updated: was late but arrived before lecture started"
  }'
```

---

## 📊 Полный сценарий использования

### Сценарий 1: Регистрация и запись на курс

```bash
# 1. Регистрация студента
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"student1","email":"s1@ex.com","password":"pass123","firstName":"Иван","lastName":"Петров","role":"STUDENT"}'

# 2. Вход в систему (сохраните токен!)
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"student1","password":"pass123"}'

# 3. Просмотр всех курсов
curl -X GET http://localhost:8080/api/courses

# 4. Записаться на курс (используйте свой токен!)
curl -X POST "http://localhost:8080/api/enrollments?studentId=1&courseId=1" \
  -H "Authorization: Bearer YOUR_TOKEN"

# 5. Посмотреть свои курсы
curl -X GET http://localhost:8080/api/enrollments/student/1 \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### Сценарий 2: Преподаватель создает курс и задание

```bash
# 1. Регистрация преподавателя
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"prof1","email":"p1@ex.com","password":"prof123","firstName":"Профессор","lastName":"Смит","role":"INSTRUCTOR"}'

# 2. Вход
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"prof1","password":"prof123"}'

# 3. Создать курс
curl -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer PROF_TOKEN" \
  -d '{"courseCode":"CS101","titleEn":"Programming","titleRu":"Программирование","titleKk":"Бағдарламалау","credits":3,"instructorId":2}'

# 4. Создать задание
curl -X POST http://localhost:8080/api/assignments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer PROF_TOKEN" \
  -d '{"courseId":1,"titleEn":"Lab 1","titleRu":"Лаб 1","titleKk":"Зерт 1","maxScore":100,"dueDate":"2024-12-31T23:59:59"}'

# 5. Выставить оценку студенту
curl -X POST http://localhost:8080/api/grades \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer PROF_TOKEN" \
  -d '{"enrollmentId":1,"assignmentId":1,"score":95,"feedbackEn":"Excellent!","feedbackRu":"Отлично!","feedbackKk":"Керемет!"}'
```

---

## 🔍 Советы по тестированию

### Использование Postman

1. Создайте переменную окружения `baseUrl` = `http://localhost:8080`
2. Создайте переменную `token` для хранения JWT токена
3. В разделе Authorization выберите "Bearer Token" и используйте `{{token}}`

### Использование VS Code REST Client

Установите расширение "REST Client" и создайте файл `.http`:

```http
### Переменные
@baseUrl = http://localhost:8080
@token = your_jwt_token_here

### Регистрация
POST {{baseUrl}}/api/auth/register
Content-Type: application/json

{
  "username": "test_user",
  "email": "test@example.com",
  "password": "password123",
  "role": "STUDENT"
}

### Вход
POST {{baseUrl}}/api/auth/login
Content-Type: application/json

{
  "username": "test_user",
  "password": "password123"
}

### Получить курсы
GET {{baseUrl}}/api/courses
Authorization: Bearer {{token}}
```

---

## ⚠️ Важные замечания

1. **Все даты** в формате ISO-8601: `2024-11-03T14:30:00`
2. **Токен действителен 24 часа** (настраивается в `application.properties`)
3. **Роли чувствительны к регистру**: используйте `STUDENT`, `INSTRUCTOR`, `ADMIN`
4. **Статусы в верхнем регистре**: `PRESENT`, `ACTIVE`, `COMPLETED`

Удачи в тестировании! 🚀
