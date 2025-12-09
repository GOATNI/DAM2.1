package org.books.example.controlladores;

import org.books.example.modelo.Autor;
import org.books.example.modelo.Libro;
import org.books.example.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class ControladorAutor {
    @Autowired
    AutorService autorService;
    @GetMapping
    public ResponseEntity<List<Autor>> getautores(){

        return ResponseEntity.ok(autorService.getallAutores());
    }

    @PostMapping
    public void añadirAutor(@RequestBody Autor autor){
        autorService.añadirautor(autor);
    }
    @PutMapping("/{id}")
    public void actualizarunautor(@PathVariable Long id, @RequestBody Autor autor){
        autorService.actualizarautor(id,autor);
    }
    @PostMapping("/añadirvarios")
    public ResponseEntity<List<Autor>> añadirvariosautores(@RequestBody List<Autor> autors){

        return autorService.añadirvariosautores(autors);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Autor> deleteautor(@PathVariable  Long id){
        return autorService.deleteautor(id);
    }

}
