package com.example.shiksha.controller;
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shiksha.model.Course;
import com.example.shiksha.model.Enrollment;
import com.example.shiksha.model.User;
import com.example.shiksha.service.CourseService;
import com.example.shiksha.service.EnrollmentService;

@RestController
@RequestMapping("enrollments")
public class EnrollmentController {
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private CourseService courseService;
	@PostMapping("/enroll")
	public ResponseEntity<?> enrollStudent(@RequestParam Long studentID, @RequestParam Long courseId) {
		try {
			User student = new User();
			student.setId(studentID);
			
			Course course = new Course();
			course.setCourseId(courseId);
			
			//alternative approach to verify if student and course exists or not
			
			enrollmentService.enrollStudent(studentID,courseId);
			return ResponseEntity.ok("Student enrolled successfully");
			
		}
		catch(Exception e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	//fun:1 display all available projects
	@GetMapping("/get")
	public List<Enrollment> displayAll(){
		return enrollmentService.displayAll();
	}


	//check enrollment Status
	@GetMapping("/listCourse")
	public ResponseEntity<List<Course>> getAllCourses(){

		List<Course> courses = courseService.getAllCourses();

		return ResponseEntity.ok(courses);
	}
}
