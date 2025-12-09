package org.books.example.modelo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.datetime.DateFormatter;

import java.text.DateFormat;
import java.util.Date;
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String Titulo;
    String Categoria;
    Date FechaPublicacion;
    String ISBN;

    @ManyToOne
    Autor autor;
}
