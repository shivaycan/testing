import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Submission {
    @Id
    @GeneratedValue
    private long SubmissionId;

    @JoinColumn(name = "assessment_id")
    Assessment assessment;

    @JoinColumn(name = "student_id")
    User student;

    private int score;

}
