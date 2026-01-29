package controller;

import Repositorio.autoresRefRepository;
import modelo.AutoreRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class autorRefController {
    @Autowired
    autoresRefRepository autorRepository;

    @GetMapping
    public ResponseEntity<List<AutoreRef>> getallautores(){
        return ResponseEntity.ok(autorRepository.findAll());
    }
}
