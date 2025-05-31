
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public String addCourse(Course course) {
		courseRepository.save(course);
		return "Data saved";
	}

	public Course getById(Integer cid) {
		Optional<Course> findById = courseRepository.findById(cid);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public String deleteById(Integer cid) {
		if (courseRepository.existsById(cid)) {
			courseRepository.deleteById(cid);
			return "Deleted successfully";
		} else {
			return "No Record Found";
		}
	}
	
	public String updateById(Integer cid, Course course) {
		Optional<Course> existCourse = courseRepository.findById(cid);
		if (existCourse.isPresent()) {
			Course updatCourse = existCourse.get();
			updatCourse.setName(course.getName());
			updatCourse.setPrice(course.getPrice());
			courseRepository.save(updatCourse);
			return "Record updated successfully";
		}
		return "No Record Found";
		
		
	}

}
