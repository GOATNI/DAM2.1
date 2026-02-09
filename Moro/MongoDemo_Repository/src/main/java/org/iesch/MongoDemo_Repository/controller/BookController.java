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


    /* /api/books/search/titulo?q=.... */

    @GetMapping("/search/titulo")
    public ResponseEntity<List<Book>> buscarPorTitulo(@RequestParam String q) {
        return ResponseEntity.ok(bookRepository.findByTituloContainingIgnoreCase(q));
    }

    /*/api/books/search/categoria?cat=.... */
    @GetMapping("/search/categoria")
    public ResponseEntity<List<Book>> buscarPorCategoria(@RequestParam String cat) {
        return ResponseEntity.ok(bookRepository.findByCategorias(cat));
    }

    /**
     * /api/books/search/autor?nombre=...
     * El nombre del autor esta en un documento enbebido.
     */
    @GetMapping("/search/autor")
    public ResponseEntity<List<Book>> buscarPorNombreAutor(@RequestParam String nombre) {
        return ResponseEntity.ok(bookRepository.findByAutoresNombre(nombre));
    }
    /**
     * /api/books/search/rango-precio?min=...&max=...
     */
    @GetMapping("/search/rango-precio")
    public ResponseEntity<List<Book>> buscarPorRangoPrecio(@RequestParam double min, @RequestParam double max) {
        return ResponseEntity.ok(bookRepository.findByPrecioBetween(min, max));
    }

    /**
     * /api/books/search/recientes?anio=...
     */
    @GetMapping("/search/recientes")
    public ResponseEntity<List<Book>> buscarPorAnioPublicacion(@RequestParam Integer anio) {
        return ResponseEntity.ok(bookRepository.findByAnioPublicacionGreaterThan(anio));
    }

    //Query personalizado
    /**
     * / api/books/search/nativo/autor?nombre=...
     * El nombre del autor esta en un documento enbebido., usando @Query
     */
    @GetMapping("/search/nativo/autor")
    public ResponseEntity<List<Book>> buscarNativoPorNombreAutor(@RequestParam String nombre) {
        return ResponseEntity.ok(bookRepository.buscarPorNombre(nombre));
    }

    /**
     * /api/books/search/nativo/precio-anio?precio=...&anio=...
     * Usando @Query
     */
    @GetMapping("/search/nativo/precioAnio")
    public ResponseEntity<List<Book>> buscarNativoPrecioMenorYAnioMayor(@RequestParam double precio, @RequestParam Integer anio) {
        return ResponseEntity.ok(bookRepository.buscarPorPrecioInferiorYAnioMayor(precio, anio));
    }
}


