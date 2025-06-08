package com.example.demo.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    private Course course;

    @BeforeEach
    public void setup() {
        course = new Course();
        course.setCid(1);
        course.setName("Java Programming");
        course.setPrice(100.0);
    }

    @Test
    @DisplayName("Test add course")
    void testAddCourse() {
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        String result = courseService.addCourse(course);

        assertEquals("Record saved successfully", result);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    @DisplayName("Test get course by id when course exists")
    void testGetById() {
        when(courseRepository.findById(anyInt())).thenReturn(Optional.of(course));

        Course result = courseService.getById(1);

        assertEquals(course, result);
        verify(courseRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Test get course by id when course does not exist")
    void testGetByIdWhenNotFound() {
        when(courseRepository.findById(anyInt())).thenReturn(Optional.empty());

        Course result = courseService.getById(1);

        assertNull(result);
        verify(courseRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Test get all courses")
    void testGetAllCourses() {
        when(courseRepository.findAll()).thenReturn(List.of(course));

        List<Course> result = courseService.getAllCourses();

        assertEquals(List.of(course), result);
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test delete course by id when course exists")
    void testDeleteByIdWhenExists() {
        when(courseRepository.existsById(anyInt())).thenReturn(true);

        String result = courseService.deleteById(1);

        assertEquals("Deleted successfully", result);
        verify(courseRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Test delete course by id when course does not exist")
    void testDeleteByIdWhenNotExists() {
        when(courseRepository.existsById(anyInt())).thenReturn(false);

        String result = courseService.deleteById(1);

        assertEquals("No Record Found", result);
        verify(courseRepository, times(0)).deleteById(anyInt());
    }

    @Test
    @DisplayName("Test update course by id when course exists")
    void testUpdateByIdWhenExists() {
        when(courseRepository.findById(anyInt())).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course updatedCourse = new Course();
        updatedCourse.setName("Updated Course Name");
        updatedCourse.setPrice(150.0);

        String result = courseService.updateById(1, updatedCourse);

        assertEquals("Record updated successfully", result);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    @DisplayName("Test update course by id when course does not exist")
    void testUpdateByIdWhenNotExists() {
        when(courseRepository.findById(anyInt())).thenReturn(Optional.empty());

        Course updatedCourse = new Course();
        updatedCourse.setName("Updated Course Name");
        updatedCourse.setPrice(150.0);

        String result = courseService.updateById(1, updatedCourse);

        assertEquals("No Record Found", result);
        verify(courseRepository, times(0)).save(any(Course.class));
    }
}

