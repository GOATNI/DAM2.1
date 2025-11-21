package org.iesch.ad.NconsultasYmas.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="autores")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;


    private String nacionalidad;

    //Por defecto es LAZY ->N+1 consultas
    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Libro> libros = new ArrayList<>();


    public Autor(String nombre, String apellido, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
    }

}
