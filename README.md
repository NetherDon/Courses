Стек технологий:
- Java 17
- Spring Boot 3.3.4
- Docker 27.2.0
- PosgtreSQL 17.0

Для запуска приложения нужно выполнить следующую команду
```console
docker-compose up
```
После чего создадутся два контейнера:
- WebApplication (localhost:8080)
- PostgreSQL (localhost:5432)

В приложении реализовано 5 запросов:
- /courses (GET) - возвращает список всех курсов
- /courses/{id} (GET) - возвращает курс по его id
- /students (GET) - возвращает список всех студентов
- /enrollments (GET) - возвращает список всех записей на курсы
- /enroll?studentId=?&courseId=? (PUT) - записывает студента (studentId) на курс (courseId), если это возможно