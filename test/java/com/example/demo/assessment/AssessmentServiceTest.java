package com.example.shiksha.service;

import com.example.shiksha.model.Assessment;
import com.example.shiksha.model.Course;
import com.example.shiksha.model.Role;
import com.example.shiksha.model.User;
import com.example.shiksha.repository.AssessmentRepository;
import com.example.shiksha.repository.CourseRepository;
import com.example.shiksha.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceTest {

    @Mock
    private AssessmentRepository assessmentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private AssessmentService assessmentService;

    private User instructor;
    private Course course;
    private Assessment assessment;

    @BeforeEach
    void setUp() {
        instructor = new User();
        instructor.setId(1L);
        instructor.setRole(Role.INSTRUCTOR);

        course = new Course();
        course.setCourseId(101L);
        course.setInstructor(instructor);

        assessment = new Assessment();
        assessment.setAssessmentId(201L);
        assessment.setCourse(course);
        assessment.setInstructor(instructor);
        assessment.setType("Quiz");
        assessment.setMaxScore(100);
    }

    @Test
    void testCreateAssessment_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(courseRepository.findById(101L)).thenReturn(Optional.of(course));
        when(assessmentRepository.save(any(Assessment.class))).thenReturn(assessment);

        Assessment result = assessmentService.createAssessment(assessment, 1L, 101L);

        assertNotNull(result);
        assertEquals("Quiz", result.getType());
        verify(assessmentRepository, times(1)).save(any(Assessment.class));
    }

    @Test
    void testDeleteAssessment_Success() {
        when(assessmentRepository.findById(201L)).thenReturn(Optional.of(assessment));

        assessmentService.deleteById(1L, 201L);

        verify(assessmentRepository, times(1)).deleteById(201L);
    }
}