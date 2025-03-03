
package com.example.demo.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.example.shiksha.model.Course;
import com.example.shiksha.model.User;

import static org.junit.jupiter.api.Assertions.*;

public class CourseModelTest {

    private Course course;
    private User instructor;

    @BeforeEach
    public void setUp() {
        instructor = new User();
        instructor.setId(1L);
        instructor.setName("Instructor Name");
        course = new Course();
        course.setCourseId(101L);
        course.setTitle("Sample Course");
        course.setDescription("Sample Description");
        course.setContentURL("http://example.com/content");
        course.setInstructor(instructor);
    }

    @AfterEach
    public void tearDown() {
        course = null;
        instructor = null;
    }

    @Test
    public void testCourseGettersAndSetters() {
        assertEquals(101L, course.getCourseId());
        assertEquals("Sample Course", course.getTitle());
        assertEquals("Sample Description", course.getDescription());
        assertEquals("http://example.com/content", course.getContentURL());
        assertEquals(instructor, course.getInstructor());
    }
}
