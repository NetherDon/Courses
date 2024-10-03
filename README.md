## Стек технологий
- Java 17
- Spring Boot 3.3.4
- Docker 27.2.0
- PosgtreSQL 17.0

## Общее описание
Для запуска приложения нужно выполнить следующую команду
```console
docker-compose up
```

После запуска создадутся два контейнера:
- WebApplication (порт: 8080)
- PostgreSQL (порт: 5432)

База данных состоит из 3 таблиц:
- courses
- students
- enrollments

Автоматически в них заносится информация о 4-х студентах и 4-х курсах. Время для каждого курса устанавливается относительно даты запуска приложения.
- 1-й курс: 
    - 20 мест 
    - время регистрации: с now() до now() + 2 дня
- 2-й курс:
    - 2 места
    - время регистрации: с now() - день до now() + 3 дня
- 3-й курс:
    - 10 мест
    - время регистрации: с now() - 4 дня до now() - 2 дня
- 4-й курс:
    - 10 мест
    - время регистрации: с now() + 4 дня до now() + 6 дня

В приложении реализовано 5 запросов:
- /courses (GET) - возвращает список всех курсов
- /courses/{id} (GET) - возвращает курс по его id
- /students (GET) - возвращает список всех студентов
- /enrollments (GET) - возвращает список всех записей на курсы
- /enroll?studentId=?&courseId=? (PUT) - записывает студента (studentId) на курс (courseId), если это возможно