package com.example.shiksha.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.shiksha.model.Course;
import com.example.shiksha.model.Role;
import com.example.shiksha.model.User;
import com.example.shiksha.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Autowired
    private ObjectMapper objectMapper;

    private Course course;
    private User instructor;

    @BeforeEach
    void setUp() {
        instructor = new User();
        instructor.setId(1L);
        instructor.setName("John Doe");
        instructor.setRole(Role.INSTRUCTOR);

        course = new Course();
        course.setCourseId(1L);
        course.setTitle("Java Programming");
        course.setDescription("Learn Java from scratch");
        course.setInstructor(instructor);
    }

    @Test
    void testCreateOrUpdateCourse() throws Exception {
        when(courseService.createOrUpdate(any(Course.class), eq(1L))).thenReturn(course);

        mockMvc.perform(post("/createCourse/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Java Programming"));
    }

    @Test
    void testGetAllCourses() throws Exception {
        when(courseService.getAllCourses()).thenReturn(Arrays.asList(course));

        mockMvc.perform(get("/getCourse")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Java Programming"));
    }

    @Test
    void testGetCourseById() throws Exception {
        when(courseService.getCourseById(1L)).thenReturn(Optional.of(course));

        mockMvc.perform(get("/getCourseById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Java Programming"));
    }

    @Test
    void testDeleteCourse() throws Exception {
        doNothing().when(courseService).deleteCourse(1L, 1L);

        mockMvc.perform(delete("/delCourse/1?userId=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted successfully"));
    }
}