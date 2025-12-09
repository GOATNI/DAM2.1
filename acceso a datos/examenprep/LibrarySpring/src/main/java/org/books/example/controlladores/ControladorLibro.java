package org.books.example.controlladores;

import org.books.example.modelo.Libro;
import org.books.example.service.Libroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Libros")
public class ControladorLibro {
    @Autowired
    Libroservice libroservice;

    @GetMapping
    public ResponseEntity<List<Libro>> getlibros(){

        return ResponseEntity.ok(libroservice.getallLibros());
    }

    @PostMapping
    public void añadirlibro(@RequestBody Libro libro){
        libroservice.añadirlibro(libro);
    }
    @PutMapping("/{id}")
    public void actualizarunlibro(@PathVariable Long id, @RequestBody Libro libro){
        libroservice.actualizarlibros(id,libro);
    }
    @PostMapping("/añadirvarios")
    public ResponseEntity<List<Libro>> añadirvarioslibro(@RequestBody List<Libro> libros){

        return libroservice.añadirvarioslibro(libros);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Libro> deletelibro(@PathVariable  Long id){
        return libroservice.deletelibro(id);
    }
}
