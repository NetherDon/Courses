package ru.test.course;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

import ru.test.course.models.CourseModel;
import ru.test.course.models.EnrollmentModel;
import ru.test.course.models.RegistrationResult;
import ru.test.course.models.StudentModel;
import ru.test.course.models.repositories.CourseRepository;
import ru.test.course.models.repositories.EnrollmentRepository;
import ru.test.course.models.repositories.StudentRepository;

@Controller
public class MainController 
{
    @Autowired 
    private CourseRepository courses;
    @Autowired
    private StudentRepository students;
    @Autowired
    private EnrollmentRepository enrollments;

    @GetMapping("/courses")
    public ResponseEntity<Iterable<CourseModel>> getCourseList()
    {
        return ResponseEntity.ok(this.courses.findAll());
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<?> getCourse(@PathVariable long id)
    {
        Optional<CourseModel> model = this.courses.findById(id);
        return model.map(ResponseEntity::ok)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/students")
    public ResponseEntity<Iterable<StudentModel>> getStudentList()
    {
        return ResponseEntity.ok(this.students.findAll());
    }

    @GetMapping("/enrollments")
    public ResponseEntity<Iterable<EnrollmentModel>> getEnrollmentList()
    {
        return ResponseEntity.ok(this.enrollments.findAll());
    }

    @PutMapping("/enroll")
    public ResponseEntity<?> enroll(TimeZone timeZone, long courseId, long studentId)
    {
        CourseModel course = this.courses.findById(courseId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown Course")
        );

        StudentModel student = this.students.findById(studentId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown Student")
        );

        ZonedDateTime userDateTime = ZonedDateTime.now(timeZone.toZoneId());
        
        RegistrationResult result = this.enrollments.enroll(course, student, userDateTime);
        return ResponseEntity.ok(result.createResponseBody());
    }
}
