package ru.test.course.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "enrollment")
public class EnrollmentModel 
{
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseModel course;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentModel studentId;

    public int getId() { return this.id; }
    public CourseModel getCourse() { return this.course; }
    public StudentModel getStudent() { return this.studentId; }
}
