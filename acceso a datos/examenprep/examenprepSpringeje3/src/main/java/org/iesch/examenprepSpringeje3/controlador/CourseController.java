package org.iesch.examenprepSpringeje3.controlador;

import org.iesch.examenprepSpringeje3.modelo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.iesch.examenprepSpringeje3.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getcourses(){
        return ResponseEntity.ok(courseService.getallcourses());
    }
    @PutMapping
    public void addACourse(@RequestBody Course course){
        courseService.addAcourse(course);
    }
    @PostMapping("/addmany")
    public void addManyCourse(@RequestBody List<Course> courses){
        courseService.addManyCourse(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getASpecificCourse(@PathVariable Long id){
        return (ResponseEntity<?>) courseService.getaSpecificCourse(id);
    }
}
