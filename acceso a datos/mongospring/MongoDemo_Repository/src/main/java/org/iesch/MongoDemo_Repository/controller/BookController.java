package org.iesch.MongoDemo_Repository.controller;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.repositorio.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    //CRUD.
    //GET ALL
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        // Lógica para obtener todos los libros
        return ResponseEntity.ok(bookRepository.findAll()); // Reemplazar null con la lista de libros obtenida
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Optional<Book>> libroporid (@PathVariable String id){
        Optional<Book> book = bookRepository.findById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> creartebook(@RequestBody Book libro){
        Book save = bookRepository.save(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updatebook(@PathVariable String id, @RequestBody Book libro){
        if (bookRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        libro.setId(id);
        Book updatedbook = bookRepository.save(libro);
        return ResponseEntity.ok(updatedbook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletebook(@PathVariable String id){
        if (bookRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/search/{titulo}")
    public ResponseEntity<List<Book>> serchbytitle (@PathVariable String titulo){
        return ResponseEntity.ok(bookRepository.findByTituloContainingIgnoreCase(titulo));
    }

    @GetMapping("/search/Autor/{autor}")
    public ResponseEntity<List<Book>> nombre_autor (@PathVariable String autor){
        return ResponseEntity.ok(bookRepository.findByAutorNombreContainingIgnoreCase(autor));
    }


}
