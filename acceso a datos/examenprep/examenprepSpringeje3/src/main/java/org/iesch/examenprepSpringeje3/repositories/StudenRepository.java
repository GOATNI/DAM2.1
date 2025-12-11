package org.iesch.examenprepSpringeje3.repositories;

import org.iesch.examenprepSpringeje3.modelo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudenRepository extends JpaRepository<Student,Long> {
     List<Student> findByCourseIdAndAge(Long courseId, int age);
     List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByNameContainingIgnoreCaseAndAgeBetweenAndCourse_NameContainingIgnoreCase(String name, int minAge, int maxAge, String courseName);

    Optional<Student> findByIdAndAge(Long id, int age);

}
