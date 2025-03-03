package com.example.demo.enrollment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import com.example.shiksha.service.EnrollmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.shiksha.model.Course;
import com.example.shiksha.model.Enrollment;
import com.example.shiksha.model.User;
import com.example.shiksha.repository.CourseRepository;
import com.example.shiksha.repository.EnrollmentRepository;
import com.example.shiksha.repository.UserRepository;

class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    private User student;
    private Course course;
    private Enrollment enrollment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student = new User();
        student.setId(1L);

        course = new Course();
        course.setCourseId(1L);

        enrollment = new Enrollment();
        enrollment.setEnrollmentId(1L);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setProgress(0);
    }

    @Test
    void testEnrollStudent_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(enrollmentRepository.findByStudentAndCourse(student, course)).thenReturn(null);
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);

        Enrollment savedEnrollment = enrollmentService.enrollStudent(1L, 1L);

        assertNotNull(savedEnrollment);
        assertEquals(student, savedEnrollment.getStudent());
        assertEquals(course, savedEnrollment.getCourse());
    }

    @Test
    void testGetAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        enrollments.add(enrollment);

        when(enrollmentRepository.findAll()).thenReturn(enrollments);

        List<Enrollment> result = enrollmentService.displayAll();
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateProgress() {
        when(enrollmentRepository.findByStudentIdAndCourseCourseId(1L, 1L))
                .thenReturn(Optional.of(enrollment));

        String result = enrollmentService.updateProgress(1L, 1L, 50);
        assertEquals("Progress updated: 50%", result);
    }
}