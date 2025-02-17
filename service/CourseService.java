package com.example.shiksha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shiksha.Repository.CourseRepository;
import com.example.shiksha.Repository.UserRepository;
import com.example.shiksha.model.Course;
import com.example.shiksha.model.Role;
import com.example.shiksha.model.User;


@Service
public class CourseService {
    
	@Autowired
    private CourseRepository courseRepository;
	@Autowired
	private UserRepository userRepository;
    
	// to get the details
	public  Course createOrUpdate(Course course,Long userId) {
		User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		if(user.getRole()!=Role.INSTRUCTOR) {
			throw new RuntimeException("only Instructor can create or update courses");
		}
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
	public void deleteCourse(Long id,Long userId) {
		try {
			User user=userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user not found"));
			Course course =courseRepository.findById(id).orElseThrow(() -> new RuntimeException("course not found"));
//			User user=course.getInstructor();
			if(!user.getRole().equals(Role.INSTRUCTOR) || !course.getInstructor().getId().equals(userId)) {
				throw new RuntimeException("you are not allowed to delete");
			}
			courseRepository.deleteById(id);
			
		}catch(Exception e) {
			throw new RuntimeException("Error deleting course:"+e.getMessage());
		}
		
	}
	
}

