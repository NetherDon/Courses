package ru.test.course.models;

import java.util.Map;

public enum RegistrationResult 
{
    REGISTERED("The student has successfully registered for the course"),
    ALREADY_REGISTERED("The student is already registered for this course"),
    TIME_IS_OVER("A student cannot enroll in a course because the enrollment time has expired"),
    EARLY_REGISTRATION("A student cannot enroll in a course because registration has not opened yet"),
    COURSE_COMPLETED("The student could not be enrolled in the course because they ran out of places");
    
    private final String message;

    private RegistrationResult(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }

    public Map<String, Object> createResponseBody()
    {
        return Map.of(
            "result", this.name(), 
            "code", this.ordinal(), 
            "message", this.message
        );
    }
}
