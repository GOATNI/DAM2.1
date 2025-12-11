package org.iesch.examenprepSpringeje3.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    String name;
    @NotBlank @Email
    String email;
    @NotNull @Min(18) @Max(100)
    int age;
    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

}
