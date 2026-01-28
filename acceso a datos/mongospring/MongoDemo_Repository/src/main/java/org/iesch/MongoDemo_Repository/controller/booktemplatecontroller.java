package org.iesch.MongoDemo_Repository.controller;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.iesch.MongoDemo_Repository.service.MongoTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("api/book/template")
public class booktemplatecontroller {
    @Autowired
    MongoTemplateService bookService;
    //buscarlibros por titulo

    @GetMapping("search/Titulo")
    public ResponseEntity<List<Book>> listadelibros(@RequestParam String q){
        return ResponseEntity.ok(bookService.findByTituloIgnoreCase(q));
    }
    @GetMapping("search/categoria")
    public ResponseEntity<List<Book>> buscarporCategoia(@RequestParam String q){
        return ResponseEntity.ok(bookService.findByCategoriasIgnoreCase(q));
    }
    @GetMapping("search/autor")
    public ResponseEntity<List<Book>> buscarporAutor(@RequestParam String q){
        return ResponseEntity.ok(bookService.findByAutorIgnoreCase(q));
    }
    @GetMapping("search/maxmin")
    public ResponseEntity<List<Book>> buscarporprecio(@RequestParam double min,@RequestParam double max){
        return ResponseEntity.ok(bookService.findByprecioIgnoreCase(max,min));
    }
    /**
     * buscar por año de publicacion
     */

    /***
     * buscar libros dipues de un tiempo xxx y con un rengo de precio especifico
     */



}
