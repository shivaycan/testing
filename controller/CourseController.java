package com.example.shiksha.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shiksha.model.Course;
import com.example.shiksha.model.User;
import com.example.shiksha.service.CourseService;
 
@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private  CourseService courseService;
	

	
//	public CourseController(CourseService courseService) {
//		this.courseService = courseService;
//	}
	
	@PostMapping("/createCourse/{id}")
	public ResponseEntity<Course> createOrUpdateCourse(@RequestBody Course course,@PathVariable Long id){
		Course savedCourse = courseService.createOrUpdate(course,id);
		return ResponseEntity.ok(savedCourse);
	}
	
	//get all courses[
	@GetMapping("/getCourse")
	public ResponseEntity<List<Course>> getAllCourses(){
		
		List<Course> courses = courseService.getAllCourses();
		
		return ResponseEntity.ok(courses);
	}
	//get course by id
	@GetMapping("/getCourseById")
	public ResponseEntity <Course> getCourseById(@PathVariable Long id){
		Optional<Course> course = courseService.getCourseById(id);
		return course.map(ResponseEntity::ok).orElseGet( () -> ResponseEntity.notFound().build() );
	}
	
	//Delete course by id
	@DeleteMapping("/delCourse")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
		courseService.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}
	
}
