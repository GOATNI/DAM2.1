package org.iesch.ad.NconsultasYmas.servicio;

import jakarta.transaction.Transactional;
import org.iesch.ad.NconsultasYmas.modelo.Autor;
import org.iesch.ad.NconsultasYmas.modelo.Libro;
import org.iesch.ad.NconsultasYmas.repositorio.AutorRepositorio;
import org.iesch.ad.NconsultasYmas.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Nmas1DemoService {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void mostrarProblemaNmas1() {
        System.out.println("Mostrar los autores y sus libros: ");

        List<Autor> autores = autorRepositorio.findAll();
        System.out.println("Autores : "+ autores.size());

        System.out.println("Accedemos a los libros");
        for (Autor autor: autores){
            System.out.println("Autor: "+autor.getNombre()+" "+autor.getApellido());
            List<Libro> libros = autor.getLibros();
            System.out.println("  Tiene: "+libros.size());
            for (Libro libro:libros){
                System.out.println("-"+libro.getTitulo()+ " "+libro.getAnioPublicacion());
            }
        }
    }
    @Transactional
    public void solucionFetchJoin() {
        //Tenemos que hacer una consulta que traiga los libros y los autores de una
        System.out.println("Consulta con Fetch");

        List<Autor> autores = autorRepositorio.findAllConLibros();


        System.out.println("Autores : "+ autores.size());

        System.out.println("Accedemos a los libros");
        for (Autor autor: autores) {
            System.out.println("Autor: " + autor.getNombre() + " " + autor.getApellido());
            List<Libro> libros = autor.getLibros();
            System.out.println("  Tiene: " + libros.size());
            for (Libro libro : libros) {
                System.out.println("-" + libro.getTitulo() + " " + libro.getAnioPublicacion());
            }
        }



    }
}
