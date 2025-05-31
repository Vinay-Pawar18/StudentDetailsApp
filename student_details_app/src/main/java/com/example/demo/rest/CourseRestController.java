package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;

@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
@RestController
@RequestMapping("/test")
public class CourseRestController {

	@Autowired
	private CourseService courseService;

	@PostMapping("/course")
	public ResponseEntity<String> createCourse(@RequestBody Course course) {
		String status = courseService.addCourse(course);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}

	@GetMapping("/course/{cid}")
	public ResponseEntity<Course> getCourse(@PathVariable String cid) {
		Integer courseId = Integer.parseInt(cid);
		Course course = courseService.getById(courseId);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@GetMapping("/course")
	public ResponseEntity<List<Course>> getAllCourses() {
		List<Course> allCourse = courseService.getAllCourses();
		return new ResponseEntity<>(allCourse, HttpStatus.OK);
	}

	@DeleteMapping("/course/{cid}")
	public ResponseEntity<String> deleteCourse(@PathVariable String cid) {
		Integer courseId = Integer.parseInt(cid);
		String status = courseService.deleteById(courseId);
		return new ResponseEntity<>(status, HttpStatus.NO_CONTENT);
	}

	@PutMapping("/course/{cid}")
	public ResponseEntity<String> updateById(@PathVariable String cid, @RequestBody Course course) {
		Integer courseId = Integer.parseInt(cid);
		String status = courseService.updateById(courseId, course);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
}
