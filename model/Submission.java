package com.example.shiksha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {

    @Id
    @GeneratedValue
    private Long submissionId;

    @ManyToOne // many submission can be submitted by one student
    @JoinColumn(name="assessment_id")
    private Assessment assessment;

    @ManyToOne
    @JoinColumn(name="student_id")
    private User student;

    @ElementCollection
    @CollectionTable(name="submitted_answers", joinColumns = @JoinColumn(name="submission_id"))
    @MapKeyColumn(name="question_id")
    @Column(name="student_answers")
    private Map<Long,String> answers;

    private Integer score;

    private String status; //SUBMITTED or GRADED

    public Submission(Assessment assessment,User student, Map<Long,String> answers){
        this.assessment = assessment;
        this.student = student;
        this.answers = answers;
    }

}
