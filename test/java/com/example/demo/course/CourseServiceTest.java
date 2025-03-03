package com.example.shiksha.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.shiksha.model.Course;
import com.example.shiksha.model.Role;
import com.example.shiksha.model.User;
import com.example.shiksha.repository.CourseRepository;
import com.example.shiksha.repository.UserRepository;

class CourseServiceTest {

	@InjectMocks
	private CourseService courseService;

	@Mock
	private CourseRepository courseRepository;

	@Mock
	private UserRepository userRepository;

	private Course course;
	private User instructor;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

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
	void testCreateOrUpdateCourse() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(instructor));
		when(courseRepository.save(any(Course.class))).thenReturn(course);

		Course savedCourse = courseService.createOrUpdate(course, 1L);

		assertNotNull(savedCourse);
		assertEquals("Java Programming", savedCourse.getTitle());
	}

	@Test
	void testGetAllCourses() {
		when(courseRepository.findAll()).thenReturn(Arrays.asList(course));

		List<Course> courses = courseService.getAllCourses();

		assertEquals(1, courses.size());
		assertEquals("Java Programming", courses.get(0).getTitle());
	}

	@Test
	void testGetCourseById() {
		when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

		Optional<Course> retrievedCourse = courseService.getCourseById(1L);

		assertTrue(retrievedCourse.isPresent());
		assertEquals("Java Programming", retrievedCourse.get().getTitle());
	}

	@Test
	void testDeleteCourse_Success() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(instructor));
		when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

		assertDoesNotThrow(() -> courseService.deleteCourse(1L, 1L));
		verify(courseRepository, times(1)).deleteById(1L);
	}

	@Test
	void testDeleteCourse_Failure_NotInstructor() {
		User student = new User();
		student.setId(2L);
		student.setRole(Role.STUDENT);

		when(userRepository.findById(2L)).thenReturn(Optional.of(student));
		when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

		Exception exception = assertThrows(RuntimeException.class, () -> courseService.deleteCourse(1L, 2L));
		assertEquals("you are not allowed to delete", exception.getMessage());
	}
}