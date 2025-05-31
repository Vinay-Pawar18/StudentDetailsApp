package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Course;

@Repository // it is optional because spring boot application scan this at run time
public interface CourseRepository extends JpaRepository<Course, Integer>{
	
	

}
