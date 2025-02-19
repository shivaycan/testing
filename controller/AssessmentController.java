package com.example.shiksha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.shiksha.model.Assessment;
import com.example.shiksha.service.AssessmentService;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {

	    @Autowired
	    private AssessmentService assessmentService;

	    @PostMapping("/createAssessment")
	    private ResponseEntity<?> createAssessment(@RequestBody Assessment assessment, @RequestParam Long userId,@RequestParam Long courseId){
	            Assessment savedAssessment =  assessmentService.createAssessment(assessment,userId,courseId);
	            return ResponseEntity.ok(savedAssessment);
	          //  return ResponseEntity.ok("Assessment created successfully");

	    }
}
