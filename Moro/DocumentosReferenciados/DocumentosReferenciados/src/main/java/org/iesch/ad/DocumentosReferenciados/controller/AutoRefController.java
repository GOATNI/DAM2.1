package org.iesch.ad.DocumentosReferenciados.controller;

import org.iesch.ad.DocumentosReferenciados.modelo.AutoresRef;
import org.iesch.ad.DocumentosReferenciados.repositorio.AutoresRefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/autores")
public class AutoRefController {

    @Autowired
    AutoresRefRepository autoresRefRepository;

    /***
     * CRUD
     * Get /api/autores
     */
    @GetMapping
    public ResponseEntity<List<AutoresRef>> getAllAutores() {
        return ResponseEntity.ok(autoresRefRepository.findAll());
    }
    /***
     * Get One
     * Get /api/autores/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<AutoresRef> getOne(@PathVariable String id) {
        Optional<AutoresRef> autor = autoresRefRepository.findById(id);

        return autor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    /***
     * Crear un nuevo autor
     * Post /api/autores
     */
    @PostMapping
    public ResponseEntity<AutoresRef> createAutor(@RequestBody AutoresRef nuevoAutor) {
        AutoresRef autoresRef = autoresRefRepository.save(nuevoAutor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autoresRef);
    }

    /***
     * Modificar un autor
     * Put /api/autores/autores
     */
    @PutMapping("/{id}")
    public ResponseEntity<AutoresRef> updateAutor(@PathVariable String id, @RequestBody AutoresRef autor) {
        if (!autoresRefRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        autor.setId(id);
        AutoresRef autoresRefUpdate = autoresRefRepository.save(autor);
        return ResponseEntity.ok(autoresRefUpdate);
    }

    /**
     * Borrar un autor
     * Si borrais un autor, se borran también todos los documentos que tenga referenciados
     *
     * Delete /api/autores/{id}
     *
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAutor(@PathVariable String id) {
        if (!autoresRefRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        autoresRefRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/nombre")
    public ResponseEntity<List<AutoresRef>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(autoresRefRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @GetMapping("/search/nacionalidad")
    public ResponseEntity<List<AutoresRef>> buscarPorNacionalidad(@RequestParam String pais) {
        return ResponseEntity.ok(autoresRefRepository.findByNacionalidadContainingIgnoreCase(pais));
    }

    @PostMapping("/search/nacionalidades")
    public ResponseEntity<List<AutoresRef>> buscarPorNacionalidades(@RequestBody List<String> paises) {
        return ResponseEntity.ok(autoresRefRepository.findByNacionalidadIn(paises));
    }
}
