package org.iesch.ad.DocumentosReferenciados.controller;

import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.iesch.ad.DocumentosReferenciados.repositorio.BookRefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/books-ref")
public class BookRefController {

    @Autowired
    BookRefRepository bookRefRepository;

    /***
     * CRUD
     * Get /api/books-ref
     */
    @GetMapping
    public ResponseEntity<List<BookRef>> getAllBooks() {
        return ResponseEntity.ok(bookRefRepository.findAll());
    }
    /***
     * Get One
     * Get /api/books-ref/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookRef> getOne(@PathVariable String id) {
        Optional<BookRef> book = bookRefRepository.findById(id);

        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    /***
     * Crear un nuevo book
     * Post /api/books-ref
     */
    @PostMapping
    public ResponseEntity<BookRef> createBook(@RequestBody BookRef nuevobook) {
        BookRef BookRef = bookRefRepository.save(nuevobook);
        return ResponseEntity.status(HttpStatus.CREATED).body(BookRef);
    }

    /***
     * Modificar un book
     * Put /api/books-ref/bookes
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookRef> updateBook(@PathVariable String id, @RequestBody BookRef book) {
        if (!bookRefRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        book.setId(id);
        BookRef BookRefUpdate = bookRefRepository.save(book);
        return ResponseEntity.ok(BookRefUpdate);
    }

    /**
     * Borrar un book
     * Si borrais un book, se borran también todos los documentos que tenga referenciados
     *
     * Delete /api/books-ref/{id}
     *
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        if (!bookRefRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRefRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Buscar libros por el id del autor
     */
    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<BookRef>> buscarLibrosPorIdAutor(@PathVariable String autorId) {
        List<BookRef> books = bookRefRepository.findByAutoresId(autorId);
        return ResponseEntity.ok(books);
    }

    /**
     * Buscar libros con precio inferior a un valor dado y año de publicación posterior a un año dado
     */
    @GetMapping("/search/precio-anio")
    public ResponseEntity<List<BookRef>> buscarPorPrecioAnioReciente(@RequestParam Double precio, @RequestParam Integer anio) {

        return ResponseEntity.ok(bookRefRepository.buscarPorPrecioInferiorYAnioSuperior(precio, anio));
    }

    /**
     * Buscar los libros mas baratos de ZZZ
     */
    @GetMapping("/search/economicos-antiguos")
    public ResponseEntity<List<BookRef>> buscarEconomicosOAntiguos(@RequestParam Double precio, @RequestParam Integer anio) {

        return ResponseEntity.ok(bookRefRepository.buscarLibrosEconomicosOAntiguos(precio, anio));
    }
}
