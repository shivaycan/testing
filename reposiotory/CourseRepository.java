package com.example.shiksha.reposiotory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shiksha.model.Course;
import com.example.shiksha.model.User;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{
	
}
