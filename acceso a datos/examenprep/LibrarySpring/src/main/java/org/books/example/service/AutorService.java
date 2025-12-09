package org.books.example.service;

import org.books.example.modelo.Autor;
import org.books.example.modelo.Libro;
import org.books.example.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AutorService {
    @Autowired
    AutorRepository autorRepository;
    public List<Autor> getallAutores() {
        return autorRepository.findAll();
    }

    public void añadirautor(Autor autor) {
        autorRepository.save(autor);
    }

    public void actualizarautor(Long id, Autor autor) {
        if (autorRepository.existsById(id)){
            Autor autor1= autorRepository.findById(id).get();
            autor1.setNombre(autor.getNombre());
            autor1.setPaisdeorigen(autor.getPaisdeorigen());
            autor1.setLibros(autor1.getLibros());
            autorRepository.save(autor);
        }
    }

    public ResponseEntity<List<Autor>> añadirvariosautores(List<Autor> autors) {
        autorRepository.saveAll(autors);
        return ResponseEntity.ok(autors);
    }

    public ResponseEntity<Autor> deleteautor(Long id) {
        Autor autor = autorRepository.findById(id).get();
        autorRepository.deleteById(id);
        return ResponseEntity.ok(autor);
    }
}
