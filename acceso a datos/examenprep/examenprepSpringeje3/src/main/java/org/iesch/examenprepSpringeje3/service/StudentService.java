package org.iesch.examenprepSpringeje3.service;

import org.iesch.examenprepSpringeje3.modelo.Student;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.iesch.examenprepSpringeje3.repositories.StudenRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudenRepository studenRepository;

    public @Nullable Object getallStudents() {
        return studenRepository.findAll();
    }

    public void addAstudent(Student student) {
        studenRepository.save(student);
    }

    public void addManyStudents(List<Student> students) {
        studenRepository.saveAll(students);
    }

    public Object getaSpecificStudent(Long id) {
        return studenRepository.findById(id);
    }

    public List<Student> getStudentsByCourseAndAge(Long courseId, int age) {
        return studenRepository.findByCourseIdAndAge(courseId, age);
    }

    public Object getbyespecificname(String namelike) {
        return studenRepository.findByNameContainingIgnoreCase(namelike);
    }

    public List<Student> searchStudents(String name, int minAge, int maxAge, String courseName) {
        return studenRepository.findByNameContainingIgnoreCaseAndAgeBetweenAndCourse_NameContainingIgnoreCase(
                name,
                minAge,
                maxAge,
                courseName
        );
    }

    public Optional<Student> getstudentbyidandage(Long id, int age) {
        return studenRepository.findByIdAndAge(id,age);
    }
}
