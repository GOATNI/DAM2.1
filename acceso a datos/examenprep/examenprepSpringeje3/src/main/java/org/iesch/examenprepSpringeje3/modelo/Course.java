package org.iesch.examenprepSpringeje3.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    @NotBlank
   private String name;
    @NotBlank
   private String category;
    @OneToMany(mappedBy = "course")
    @JsonManagedReference
    private List<Student> students = new ArrayList<>();

}
