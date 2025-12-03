package org.iesch.ad.demoValid.controldor;

import jakarta.validation.Valid;
import org.iesch.ad.demoValid.modelo.Usuariodto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioControler {

    @PostMapping
    public ResponseEntity<String> crearusuario(@Valid @RequestBody Usuariodto usuariodto){
        System.out.println(usuariodto);
        return ResponseEntity.ok(usuariodto.toString());
    }
}
