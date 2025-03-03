package com.example.shiksha.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class AssessmentTest {

    private Assessment assessment;
    private Course course;
    private User instructor;
    @BeforeEach
    void setUp() {
        course = new Course();
        course.setCourseId(101L);
        course.setTitle("Java Programming");

        instructor = new User();
        instructor.setId(1L);
        instructor.setRole(Role.INSTRUCTOR);

        assessment = new Assessment();
        assessment.setAssessmentId(1L);
        assessment.setType("Quiz");
        assessment.setMaxScore(100);
        assessment.setCourse(course);
        assessment.setInstructor(instructor);
    }

    @Test
    void testAssessmentProperties() {
        assertThat(assessment.getAssessmentId()).isEqualTo(1L);
        assertThat(assessment.getType()).isEqualTo("Quiz");
        assertThat(assessment.getMaxScore()).isEqualTo(100);
        assertThat(assessment.getCourse()).isEqualTo(course);
        assertThat(assessment.getInstructor()).isEqualTo(instructor);
    }

    @Test
    void testSetQuestions() {
        Question q1 = new Question();
        q1.setQuestionId(1L);
        q1.setQuestionText("What is Java?");
        Question q2 = new Question();
        q2.setQuestionId(2L);
        q2.setQuestionText("Explain OOP concepts.");

        assessment.setQuestions(List.of(q1, q2));

        assertThat(assessment.getQuestions()).hasSize(2);
        assertThat(assessment.getQuestions()).contains(q1, q2);
    }
}