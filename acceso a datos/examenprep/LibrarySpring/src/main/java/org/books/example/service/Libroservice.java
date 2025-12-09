package org.books.example.service;

import org.books.example.modelo.Libro;
import org.books.example.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Libroservice {
    @Autowired
    LibrosRepository librosRepository;
    public List<Libro> getallLibros() {
        return librosRepository.findAll();
    }

    public void añadirlibro(Libro libro) {
        librosRepository.save(libro);
    }

    public void actualizarlibros(Long id, Libro libro) {
        if (librosRepository.existsById(id)){
            Libro libroactualizar = librosRepository.findById(id).get();
            libroactualizar.setTitulo(libro.getTitulo());
            libroactualizar.setISBN(libro.getISBN());
            libroactualizar.setCategoria(libro.getCategoria());
            libroactualizar.setFechaPublicacion(libro.getFechaPublicacion());
            librosRepository.save(libroactualizar);
        }
    }

    public ResponseEntity<List<Libro>> añadirvarioslibro(List<Libro> libros) {
        librosRepository.saveAll(libros);
        return ResponseEntity.ok(libros);
    }

    public ResponseEntity<Libro> deletelibro(Long id) {
        Libro libro = librosRepository.findById(id).get();
        librosRepository.deleteById(id);
        return ResponseEntity.ok(libro);
    }
}
