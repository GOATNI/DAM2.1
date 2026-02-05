package com.example.documentosreferenciados.controller;

import com.example.documentosreferenciados.modelo.BookRef;
import com.example.documentosreferenciados.repositorio.AutoresRefRepository;
import com.example.documentosreferenciados.modelo.AutoreRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorRefController {
    @Autowired
    AutoresRefRepository autorRepository;

    @GetMapping
    public ResponseEntity<List<AutoreRef>> getallautores(){
        return ResponseEntity.ok(autorRepository.findAll());
    }
    @GetMapping("/search/nombre")
    public ResponseEntity<List<AutoreRef>> buscarPorNombre(@RequestParam String nombre){
        List<AutoreRef> autores = autorRepository.FindbyNombre(nombre);
        return ResponseEntity.ok(autores);
    }
    @GetMapping("/search/nacionalidad")
    public ResponseEntity<List<AutoreRef>> buscarPorNacionalidad(@RequestParam String nacionalidad){
        List<AutoreRef> autores = autorRepository.FindbyNacionalidad(nacionalidad);
        return ResponseEntity.ok(autores);
    }
    @GetMapping("/search/{id}")
    public ResponseEntity<AutoreRef> buscarPorId(@PathVariable String id){
        AutoreRef autor = autorRepository.findById(id).orElse(null);
        if (autor != null) {
            return ResponseEntity.ok(autor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





}
