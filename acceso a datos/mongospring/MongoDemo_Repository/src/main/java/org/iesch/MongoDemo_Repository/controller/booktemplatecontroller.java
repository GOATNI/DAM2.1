package org.iesch.MongoDemo_Repository.controller;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.service.MongoTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador para búsquedas avanzadas usando MongoTemplate.
 */
@RestController
@RequestMapping("api/book/template")
public class booktemplatecontroller {
    @Autowired
    MongoTemplateService bookService;

    /**
     * Busca libros por título.
     *

     */
    @GetMapping("search/Titulo")
    public ResponseEntity<List<Book>> listadelibros(@RequestParam String q) {
        return ResponseEntity.ok(bookService.findByTituloIgnoreCase(q));
    }

    /**
     * Busca libros por categoría.
     *
     */
    @GetMapping("search/categoria")
    public ResponseEntity<List<Book>> buscarporCategoia(@RequestParam String q) {
        return ResponseEntity.ok(bookService.findByCategoriasIgnoreCase(q));
    }

    /**
     * Busca libros por autor.
     *
     */
    @GetMapping("search/autor")
    public ResponseEntity<List<Book>> buscarporAutor(@RequestParam String q) {
        return ResponseEntity.ok(bookService.findByAutorIgnoreCase(q));
    }

    /**
     * Busca libros por rango de precio.
     *
     */
    @GetMapping("search/maxmin")
    public ResponseEntity<List<Book>> buscarporprecio(@RequestParam double min, @RequestParam double max) {
        return ResponseEntity.ok(bookService.findByprecioIgnoreCase(max, min));
    }

    /**
     * Buscar por año de publicación.
     *
     */
    @GetMapping("search/anio")
    public ResponseEntity<List<Book>> buscarPorAnio(@RequestParam int anio) {
        return ResponseEntity.ok(bookService.findByAnioPublicacion(anio));
    }

    /**
     * Buscar libros después de un año específico y con un rango de precio específico.
     *
     */
    @GetMapping("search/anio-precio")
    public ResponseEntity<List<Book>> buscarPorAnioYPrecio(@RequestParam int anio, @RequestParam double min, @RequestParam double max) {
        return ResponseEntity.ok(bookService.findByAnioAndPrecioRange(anio, min, max));
    }
}
