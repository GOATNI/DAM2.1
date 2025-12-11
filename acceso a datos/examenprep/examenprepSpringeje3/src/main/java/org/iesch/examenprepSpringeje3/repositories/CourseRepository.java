package org.iesch.examenprepSpringeje3.repositories;

import org.iesch.examenprepSpringeje3.modelo.Course;
import org.iesch.examenprepSpringeje3.modelo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

}
