# 🐳 Docker Guide - Запуск через Docker

## Быстрый старт с Docker

### Вариант 1: Только база данных (рекомендуется для разработки)

```bash
# Запустить PostgreSQL и pgAdmin
docker-compose up -d postgres pgadmin

# Проверить статус
docker-compose ps

# Приложение запускать локально через IDE или Maven
mvnw.cmd spring-boot:run
```

После запуска:
- **PostgreSQL:** localhost:5432
- **pgAdmin:** http://localhost:5050
  - Email: admin@learningtracker.com
  - Password: admin

### Вариант 2: Полный запуск (БД + Приложение)

```bash
# 1. Сначала собрать JAR файл
mvnw.cmd clean package -DskipTests

# 2. Раскомментировать секцию 'app' в docker-compose.yml

# 3. Запустить все сервисы
docker-compose up -d

# 4. Проверить логи
docker-compose logs -f app
```

## Управление контейнерами

### Остановить все сервисы
```bash
docker-compose down
```

### Остановить и удалить данные
```bash
docker-compose down -v
```

### Перезапустить сервис
```bash
docker-compose restart app
```

### Посмотреть логи
```bash
# Все сервисы
docker-compose logs

# Только приложение
docker-compose logs app

# В реальном времени
docker-compose logs -f
```

## Подключение к pgAdmin

1. Откройте http://localhost:5050
2. Войдите с credentials:
   - Email: `admin@learningtracker.com`
   - Password: `admin`
3. Добавьте сервер:
   - Host: `postgres` (имя контейнера)
   - Port: `5432`
   - Database: `learning_progress_tracker`
   - Username: `postgres`
   - Password: `postgres`

## Сборка Docker образа вручную

```bash
# Собрать образ
docker build -t learning-tracker:latest .

# Запустить контейнер
docker run -d \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/learning_progress_tracker \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  --name learning-tracker-app \
  learning-tracker:latest
```

## Troubleshooting

### Порт уже занят
```bash
# Изменить порты в docker-compose.yml
# Например: "5433:5432" вместо "5432:5432"
```

### База данных не доступна
```bash
# Проверить запущен ли PostgreSQL
docker-compose ps postgres

# Перезапустить
docker-compose restart postgres
```

### Приложение не запускается
```bash
# Проверить логи
docker-compose logs app

# Убедиться что JAR файл собран
ls -l target/*.jar

# Пересобрать
mvnw.cmd clean package -DskipTests
docker-compose up -d --build app
```
