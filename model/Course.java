package com.example.shiksha.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Course {
		
	@Id
	@GeneratedValue
	private Long courseId;
	
	
	private String title;
	private String description;
	private String contentURL;
	
	
	@ManyToOne // many courses can be created by one instructor
	@JoinColumn(name = "instructor_id") // in course table, a new column will be created by the name insructor id, which will store the id of the instructor
	private User instructor;
	
}
