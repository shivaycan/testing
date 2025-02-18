package com.example.shiksha.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.shiksha.model.*;
import com.example.shiksha.reposiotory.CourseRepository;
import com.example.shiksha.reposiotory.EnrollmentRepository;
import com.example.shiksha.reposiotory.UserRepository;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Enroll student
    public Enrollment enrollStudent(Long studentId, Long courseId) {
        // Retrieve the saved student and course from the database
        User student = userRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        // Check if the student is already enrolled in the course
        Enrollment existingEnrollment = enrollmentRepository.findByStudentAndCourse(student, course);
        if (existingEnrollment != null) {
            throw new RuntimeException("Student is already enrolled");
        }

        // Create a new enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setProgress(0.0);
        // Save the enrollment
        return enrollmentRepository.save(enrollment);
    }

    // Return all enrollments
    public List<Enrollment> displayAll() {
        return enrollmentRepository.findAll();
    }
}
