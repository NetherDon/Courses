package ru.test.course.models.repositories;

import org.springframework.data.repository.CrudRepository;

import ru.test.course.models.CourseModel;

public interface CourseRepository extends CrudRepository<CourseModel, Long>
{
    
}
