package com.example.documentosreferenciados.controller;

import com.example.documentosreferenciados.modelo.BookRef;
import com.example.documentosreferenciados.service.Booktemplateservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books-ref-template")
public class BookrefControlerTemplate {
    @Autowired
    Booktemplateservice bookrefservice;
    //Crud
    //Get all
    @GetMapping
    public ResponseEntity<List<BookRef>> getAllBooks(){
        return ResponseEntity.ok(bookrefservice.buscatodos());
    }
    //bucar por titulo
    @GetMapping("/search/titulo")
    public ResponseEntity<List<BookRef>> buscarPorTitulo(@RequestParam String titulo) {
    return ResponseEntity.ok(bookrefservice.buscarPorTitulo(titulo));
    }

    //bucar por id autor
    @GetMapping("/search/autor-id/{autorId}")
    public ResponseEntity<List<BookRef>> buscarPorAutorId(@PathVariable String autorId) {
        return ResponseEntity.ok(bookrefservice.buscarPorAutorId(autorId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookRef> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(bookrefservice.buscaronId(id));
    }

    // bucar libro de un autor especifico con nombre y usando lookup
    @GetMapping("/search/autor-nombre")
    public ResponseEntity<List<BookRef>> buscarPorAutorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(bookrefservice.buscarPorAutorNombre(nombre));
    }
}
