package com.CIPP.course_api.service;

import com.CIPP.course_api.dto.CourseRequest;
import com.CIPP.course_api.dto.CourseResponse;
import com.CIPP.course_api.model.Course;
import com.CIPP.course_api.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseResponse createCourse(CourseRequest request) {
        Course course = new Course();
        course.setName(request.getName());
        course.setDescription(request.getDescription());

        Course savedCourse = courseRepository.save(course);

        return new CourseResponse(
                savedCourse.getId(),
                savedCourse.getName(),
                savedCourse.getDescription()
        );
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> new CourseResponse(
                        course.getId(),
                        course.getName(),
                        course.getDescription()
                ))
                .toList();
    }
}