

package com.example.shiksha.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Enrollments")
@Data
@NoArgsConstructor
public class Enrollment {
	
	@Id
	@GeneratedValue
	private Long enrollmentId;
	
	// User(students) 1 -> Many Enrollments < - 1 Course course
	
	@ManyToOne // many courses can be alloted to one student
	@JoinColumn(name="student_id",nullable = false)
	private User student;
	
	@ManyToOne
	@JoinColumn(name="course_id",nullable=false)
	private Course course;
	
	private double progress =0;

}
