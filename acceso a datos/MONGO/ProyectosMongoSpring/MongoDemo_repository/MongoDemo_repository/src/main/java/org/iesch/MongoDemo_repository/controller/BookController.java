package org.iesch.MongoDemo_repository.controller;

import org.iesch.MongoDemo_repository.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.iesch.MongoDemo_repository.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    //CRUD

    @GetMapping
    public ResponseEntity<List<Book>> conseguirTodosLosLibros(){
        List<Book> libros = bookRepository.findAll();
        return ResponseEntity.ok(libros);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookbyId(@PathVariable String id){
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //CREATE
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book libroGuardado = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book){

        if (!bookRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        book.setId(id);
        Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);

    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook (@PathVariable String id){

        if (!bookRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /*
    * /api/books/search/titulo?q=.....
    *
    *
    * */
    @GetMapping("/search/titulo")
    public ResponseEntity<List<Book>> buscarPorTitulo(@RequestParam String q){
        return ResponseEntity.ok(bookRepository.findByTituloContainingIgnoreCase(q));
    }

    /*
     * /api/books/search/categorias?cat=.....
     */

    @GetMapping("/search/categorias")
    public ResponseEntity<List<Book>> buscarPorCategoria(@RequestParam String cat){
        return ResponseEntity.ok(bookRepository.findByCategoriasIgnoreCase(cat));
    }

    // /api/books/search/autor?nombre=...
    //El nombre del autor esta en un documento embebido
    @GetMapping("/search/autor")
    public ResponseEntity<List<Book>> buscarPorNombreAutor(@RequestParam String nombre){

        return ResponseEntity.ok(bookRepository.findByAutoresNombre(nombre));
    }

    /*
    * /api/books/search/precio?min=...&max=...
    */
    @GetMapping("/search/precio")
    public ResponseEntity<List<Book>> buscarLibrosEntrePrecios(@RequestParam Double min, @RequestParam Double max){

        return ResponseEntity.ok(bookRepository.findByPrecioBetween(min,max));

    }

    // /api/books/search/recientes?anio=

    @GetMapping("/search/recientes")
    public ResponseEntity<List<Book>> buscarRecientes(@RequestParam Integer anio){

        return ResponseEntity.ok(bookRepository.findByAnioPublicacionGreaterThan(anio));

    }

    //=============QUERYS PERSONALIZADAS===========

    // /api/books/search/nativo/autor?nombre=...
    //El nombre del autor esta en un documento embebido y hacemos uso de @Query
    @GetMapping("/search/nativo/autor")
    public ResponseEntity<List<Book>> buscarPorNombreAutorNativo(@RequestParam String nombre){

        return ResponseEntity.ok(bookRepository.buscarPorNombreAutor(nombre));
    }

    // /api/books/search/nativo/autor?nombre=...

    @GetMapping("/search/nativo/precioAnio")
    public ResponseEntity<List<Book>> buscarPorPrecioAnioMeyor(@RequestParam Double precio, @RequestParam Integer anio){

        return ResponseEntity.ok(bookRepository.buscarPorPrecioInferiorYAnioMayor(precio,anio));
    }





}
