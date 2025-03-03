package com.example.shiksha.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.shiksha.model.Course;
import com.example.shiksha.model.Enrollment;
import com.example.shiksha.model.Role;
import com.example.shiksha.model.User;

@SpringBootTest
@Transactional // Ensures test data resets after each test
class EnrollmentRepositoryTest {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    private User student;
    private User instructor;
    private Course course;
    private Enrollment enrollment;

    @BeforeEach
    void setUp() {
        // Create and save a student
        student = new User();
        student.setName("Alice");
        student.setRole(Role.STUDENT);
        student = userRepository.save(student);

        // Create and save an instructor
        instructor = new User();
        instructor.setName("Bob");
        instructor.setRole(Role.INSTRUCTOR);
        instructor = userRepository.save(instructor);

        // Create and save a course
        course = new Course();
        course.setTitle("Spring Boot");
        course.setDescription("Learn Spring Boot with hands-on experience");
        course.setInstructor(instructor);
        course = courseRepository.save(course);

        // Create and save an enrollment
        enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setProgress(50);
        enrollment = enrollmentRepository.save(enrollment);
    }

    @AfterEach
    void tearDown() {
        // Clear all test data
        enrollmentRepository.deleteAll();
        courseRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testFindByStudentIdAndCourseCourseId() {
        Optional<Enrollment> foundEnrollment = enrollmentRepository.findByStudentIdAndCourseCourseId(student.getId(), course.getCourseId());

        assertTrue(foundEnrollment.isPresent());
        assertEquals(50, foundEnrollment.get().getProgress());
    }

    @Test
    void testFindByStudentAndCourse() {
        Enrollment foundEnrollment = enrollmentRepository.findByStudentAndCourse(student, course);

        assertNotNull(foundEnrollment);
        assertEquals(50, foundEnrollment.getProgress());
    }
}