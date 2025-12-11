package org.iesch.examenprepSpringeje3.controlador;

import org.iesch.examenprepSpringeje3.modelo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.iesch.examenprepSpringeje3.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StrudenControlador {
    @Autowired
    StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getstudents(){
        return ResponseEntity.ok(studentService.getallStudents());
    }
    @PutMapping
    public void addAStudent(@RequestBody Student student){
        studentService.addAstudent(student);
    }
    @PostMapping("/addmany")
    public void addmanystudents(@RequestBody List<Student> students){
        studentService.addManyStudents(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getASpecificStudent(@PathVariable Long id){
       return (ResponseEntity<?>) studentService.getaSpecificStudent(id);
    }

    @GetMapping("/course/{courseId}/age/{age}")
    public ResponseEntity<?> getStudentsByCourseAndAge(@PathVariable Long courseId,
                                                       @PathVariable int age) {
        return ResponseEntity.ok(studentService.getStudentsByCourseAndAge(courseId, age));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getStudentsByCourseAndAge(@PathVariable String name) {
        return ResponseEntity.ok(studentService.getbyespecificname(name));
    }

    @GetMapping("/search/name/{name}/minAge/{minAge}/maxAge/{maxAge}/Coursename/{courseName}")
    public ResponseEntity<List<Student>> search(
            @RequestParam String name,
            @RequestParam int minAge,
            @RequestParam int maxAge,
            @RequestParam String courseName
    ) {
        List<Student> results = studentService.searchStudents(name, minAge, maxAge,courseName);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/student/id/{id}/age/{age}")
    public ResponseEntity<?> getStudentByIdAndAge(@PathVariable Long id, @PathVariable int age) {
        Optional<Student> studentOpt = studentService.getstudentbyidandage(id, age);

        if (studentOpt.isPresent()) {
            return ResponseEntity.ok(studentOpt.get());
        } else {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.MULTI_STATUS.value());
            body.put("message", "Student not found with id " + id + " and age " + age);
            return ResponseEntity.ok(body);
        }
    }

}
