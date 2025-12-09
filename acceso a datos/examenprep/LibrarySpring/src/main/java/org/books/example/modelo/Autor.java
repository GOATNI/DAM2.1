package org.books.example.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String Nombre;
    String paisdeorigen;
    @JsonIgnore
    @OneToMany(mappedBy = "autor")
    List<Libro> libros;


}
