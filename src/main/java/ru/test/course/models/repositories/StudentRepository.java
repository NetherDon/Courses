package ru.test.course.models.repositories;

import org.springframework.data.repository.CrudRepository;

import ru.test.course.models.StudentModel;

public interface  StudentRepository extends CrudRepository<StudentModel, Long>
{
    
}
