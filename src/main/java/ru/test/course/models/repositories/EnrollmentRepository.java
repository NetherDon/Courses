package ru.test.course.models.repositories;

import java.time.ZonedDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ru.test.course.models.CourseModel;
import ru.test.course.models.EnrollmentModel;
import ru.test.course.models.RegistrationResult;
import ru.test.course.models.StudentModel;

public interface EnrollmentRepository extends CrudRepository<EnrollmentModel, Long>
{
    @Query(value="select enroll(:#{#course.getId()}, :#{#student.getId()}, :#{#dateTime})", nativeQuery=true)
    public RegistrationResult enroll(
        @Param("course") CourseModel course, 
        @Param("student") StudentModel student, 
        @Param("dateTime") ZonedDateTime dateTime
    );
}
