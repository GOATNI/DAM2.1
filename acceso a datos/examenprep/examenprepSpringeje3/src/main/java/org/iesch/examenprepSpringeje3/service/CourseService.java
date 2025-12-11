package org.iesch.examenprepSpringeje3.service;

import org.iesch.examenprepSpringeje3.modelo.Course;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.iesch.examenprepSpringeje3.repositories.CourseRepository;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    public @Nullable Object getallcourses() {
        return courseRepository.findAll();
    }

    public void addAcourse(Course course) {
        courseRepository.save(course);
    }

    public void addManyCourse(List<Course> courses) {
        courseRepository.saveAll(courses);
    }

    public Object getaSpecificCourse(Long id) {
        return courseRepository.findById(id);
    }
}
