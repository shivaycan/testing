package com.example.shiksha.reposiotory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.shiksha.model.*;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{
	//	List<Enrollment> findByStudentId(Long studentId);
	//	List<Enrollment> findByCourseId(Long courseId);
		
		Enrollment findByStudentAndCourse(User student,Course course);

}
