# ⚡ Быстрый старт - 5 минут до запуска!

## 🎯 Минимальная инструкция для новичков

### Шаг 1: Проверка Java (30 секунд)

Откройте командную строку (Win+R → `cmd`) и введите:

```bash
java -version
```

**Должно показать:** `java version "17.x.x"`

❌ **Если нет:** Скачайте Java 17 с https://www.oracle.com/java/technologies/downloads/

---

### Шаг 2: Установка PostgreSQL (5 минут)

1. Скачайте PostgreSQL: https://www.postgresql.org/download/
2. Установите (запомните пароль!)
3. Откройте **pgAdmin 4**
4. Создайте базу данных:
   - Правый клик на **Databases** → **Create** → **Database**
   - Имя: `learning_progress_tracker`
   - Нажмите **Save**

---

### Шаг 3: Настройка проекта (1 минута)

Откройте файл:
```
src/main/resources/application.properties
```

Найдите и измените **ВАШ ПАРОЛЬ**:

```properties
spring.datasource.password=ВАШ_ПАРОЛЬ_ОТ_POSTGRES
```

**ВАЖНО:** Замените `ВАШ_ПАРОЛЬ_ОТ_POSTGRES` на пароль, который вы установили при установке PostgreSQL!

---

### Шаг 4: Запуск приложения (1 минута)

#### Вариант A: Через командную строку

```bash
# Перейдите в папку проекта
cd c:\Users\Remus\OneDrive\Desktop\bauka

# Запустите приложение
mvnw.cmd spring-boot:run
```

#### Вариант B: Через IntelliJ IDEA

1. Откройте проект в IntelliJ IDEA
2. Найдите файл `LearningProgressTrackerApplication.java`
3. Нажмите зеленую стрелку ▶️ рядом с классом
4. Выберите **Run**

---

### Шаг 5: Проверка (30 секунд)

Откройте браузер:
```
http://localhost:8080
```

Вы должны увидеть красивую главную страницу! 🎉

---

## 🧪 Первый тест API

### 1. Регистрация пользователя

Откройте командную строку и выполните:

```bash
curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d "{\"username\":\"student1\",\"email\":\"student1@example.com\",\"password\":\"password123\",\"firstName\":\"Иван\",\"lastName\":\"Петров\",\"role\":\"STUDENT\"}"
```

**Ответ должен быть:**
```json
{
  "message": "User registered successfully! Username: student1",
  "success": true
}
```

### 2. Вход в систему

```bash
curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"username\":\"student1\",\"password\":\"password123\"}"
```

**Вы получите JWT токен!** Сохраните его для дальнейших запросов.

---

## ❗ Частые проблемы

### Проблема 1: "Cannot connect to database"

**Решение:**
1. Убедитесь, что PostgreSQL запущен
2. Проверьте пароль в `application.properties`
3. Проверьте, что база данных `learning_progress_tracker` создана

### Проблема 2: "Port 8080 already in use"

**Решение:**
Измените порт в `application.properties`:
```properties
server.port=8081
```

### Проблема 3: Maven не найден

**Решение:**
Используйте встроенный Maven Wrapper:
```bash
# Windows
mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

---

## 📚 Что дальше?

### Тестирование API:
1. Откройте файл `API_EXAMPLES.md`
2. Попробуйте примеры запросов
3. Используйте Postman для удобного тестирования

### Создание данных:
1. Зарегистрируйте преподавателя (role: INSTRUCTOR)
2. Создайте курс
3. Запишите студента на курс
4. Создайте задание
5. Выставьте оценку

### Изучение кода:
- `src/main/java/com/learningtracker/model/` - модели БД
- `src/main/java/com/learningtracker/controller/` - API эндпоинты
- `src/main/java/com/learningtracker/service/` - бизнес-логика

---

## 🎓 Готовые тестовые данные

После запуска приложения выполните эти команды для создания тестовых данных:

```bash
# 1. Создать студента
curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d "{\"username\":\"student1\",\"email\":\"s1@ex.com\",\"password\":\"pass123\",\"firstName\":\"Иван\",\"lastName\":\"Петров\",\"role\":\"STUDENT\"}"

# 2. Создать преподавателя
curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d "{\"username\":\"prof1\",\"email\":\"p1@ex.com\",\"password\":\"prof123\",\"firstName\":\"Профессор\",\"lastName\":\"Смит\",\"role\":\"INSTRUCTOR\"}"

# 3. Войти как преподаватель (СОХРАНИТЕ ТОКЕН!)
curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"username\":\"prof1\",\"password\":\"prof123\"}"
```

---

## 🚀 Готово!

Теперь у вас есть:
- ✅ Запущенное приложение
- ✅ База данных PostgreSQL
- ✅ Тестовые пользователи
- ✅ Готовые API эндпоинты

**Следующий шаг:** Откройте `API_EXAMPLES.md` и начните тестировать API!

Удачи! 🎉
