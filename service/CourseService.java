package com.example.shiksha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shiksha.model.Course;
import com.example.shiksha.model.User;
import com.example.shiksha.reposiotory.CourseRepository;
import com.example.shiksha.reposiotory.UserRepository;

@Service
public class CourseService {
    
	@Autowired
    private CourseRepository courseRepository;
	@Autowired
	private UserRepository userRepository;
    
	// to get the details
	public  Course createOrUpdate(Course course,Long userId) {
		User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		course.setInstructor(user);
		return courseRepository.save(course);
	}
	
	//to show the course details to user(student)
	public List<Course> getAllCourses(){
		return courseRepository.findAll();
	}
	
	//get course by id
	public Optional<Course> getCourseById(Long id){
		return courseRepository.findById(id);
	}
	
	//deleteCourse
	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}
	
}
