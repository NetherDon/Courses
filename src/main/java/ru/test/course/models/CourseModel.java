package ru.test.course.models;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")
public class CourseModel 
{
    @Id
    private int id;
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    @Column(name = "places", nullable = false)
    private int places;
    @Column(name = "registered", nullable = false)
    private int registered;
    @Column(name = "start_time", nullable = false)
    private ZonedDateTime startTime;
    @Column(name = "end_time", nullable = false)
    private ZonedDateTime endTime;

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public int getPlaces() { return this.places; }
    public int getRegistered() { return this.registered; }
    public int getRemainingPlaces() { return Math.max(0, this.places - this.registered); }
    public boolean isCompleted() { return this.places <= this.registered; }

    public ZonedDateTime getStartTime() { return this.startTime; }
    public ZonedDateTime getEndTime() { return this.endTime; }
}
