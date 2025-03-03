package com.example.shiksha.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnrollmentTest {

    private Enrollment enrollment;
    private User student;
    private Course course;

    @BeforeEach
    void setUp() {
        enrollment = new Enrollment();
        student = new User();
        course = new Course();

        enrollment.setEnrollmentId(1L);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setProgress(50);
    }

    @Test
    void testEnrollmentModel() {
        assertEquals(1L, enrollment.getEnrollmentId());
        assertEquals(student, enrollment.getStudent());
        assertEquals(course, enrollment.getCourse());
        assertEquals(50, enrollment.getProgress());
    }
}