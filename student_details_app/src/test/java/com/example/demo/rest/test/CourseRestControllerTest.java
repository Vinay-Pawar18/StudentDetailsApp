package com.example.demo.rest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.Course;
import com.example.demo.rest.CourseRestController;
import com.example.demo.service.CourseService;

import lombok.SneakyThrows;

@ExtendWith(MockitoExtension.class)
public class CourseRestControllerTest {


    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseRestController courseController;
    
    private Course course;
    
    @BeforeEach
    public void setup() {
        course = new Course();
        course.setCid(1);
        course.setName("Java Programming");
        course.setPrice(100.0);
    }
    

    @Test
    @DisplayName("Test for add course")
    @SneakyThrows
    void testCreateCourse() {
        when(courseService.addCourse(any(Course.class))).thenReturn("Data saved");
        // Act
        ResponseEntity<String> response = courseController.createCourse(course);
        // Assert
        assertEquals("Data saved", response.getBody());
    }
    
    @Test
    @DisplayName("Test for get course by id")
    @SneakyThrows
    void testGetCourseById() {
        when(courseService.getById(1)).thenReturn(course);

        // Act
        ResponseEntity<Course> response = courseController.getCourse("1");
        
        assertEquals(1, response.getBody().getCid());  // Additional check for course ID
        assertEquals("Java Programming", response.getBody().getName());  // Additional check for course name

        // Verify
        verify(courseService, times(1)).getById(1);  // Verify that the service was called exactly once with the correct argument

    }
    
    @Test
    @DisplayName("Test for get all course")
    @SneakyThrows
    void testGetAllCourse() {
        when(courseService.getAllCourses()).thenReturn(List.of(course));

        // Act
        ResponseEntity<List<Course>> response = courseController.getAllCourses();
        assertNotNull(response);
    }
    
    @Test
    @DisplayName("Test for delete course by id")
    @SneakyThrows
    void testDeleteCourseById() {

        when(courseService.deleteById(anyInt())).thenReturn("Deleted successfully");

        // Act
        ResponseEntity<String> response = courseController.deleteCourse("1");
        
        assertNotNull(response); // Ensure the response is not null
        assertEquals("Deleted successfully", response.getBody()); // Verify the response body
    }
    
    @Test
    @DisplayName("Test for update course by id")
    @SneakyThrows
    void testUpdateCourseById() {
        // Return a specific response when updateById is called
        when(courseService.updateById(anyInt(), any(Course.class))).thenReturn("Record updated successfully");

        // Act
        ResponseEntity<String> response = courseController.updateById("1", course);

        // Assert
        assertNotNull(response);
    }
}
