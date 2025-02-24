package com.example.shiksha.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Question {
	  	@Id
	    @GeneratedValue //(strategy = GenerationType.IDENTITY)
	    private Long questionId;

	    @ManyToOne
	    @JoinColumn(name="assessment_id")
	    private Assessment assessment;

	    private String questionText;

	    @ElementCollection
	    private List<String> options;

	    private String correctAnswer; //to store correct answers
}
