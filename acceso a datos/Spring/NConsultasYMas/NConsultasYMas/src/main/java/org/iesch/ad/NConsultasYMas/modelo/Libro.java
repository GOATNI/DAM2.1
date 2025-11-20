package org.iesch.ad.NconsultasYmas.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;//Esto igual peta, Esto crea el getter setter , el toString el compare y varias cosas mas.
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "libros")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String titulo;

    private String isbn;

    private Double precio;

    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;

    //por defecto esto va a ser EAGER y lo vamos a forzar a ser LAZY
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    public Libro(String titulo, String isbn, Double precio, Integer anioPublicacion, Autor autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.precio = precio;
        this.anioPublicacion = anioPublicacion;
        this.autor = autor;
    }


}
