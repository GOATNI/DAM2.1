package org.iesch.SegundoSpring.controler;

import org.iesch.SegundoSpring.modelo.Usuario;
import org.iesch.SegundoSpring.modelo.UsuarioDTORespuesta;
import org.iesch.SegundoSpring.modelo.UsuarioDtopeticion;
import org.iesch.SegundoSpring.servicios.usuarioservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@RestController
public class usuariocontroler {

    @Autowired
    Map<Long,Usuario> usuarios;
    @Autowired
    usuarioservice Usuarioservice;

    @PostMapping("/usuario")
    public ResponseEntity<?> registar(@RequestBody UsuarioDtopeticion usuariodto){
        Usuario user = Usuario.builder().nombre(usuariodto.getNombre()).passwd(usuariodto.getPasswd()).build();
        Usuario usuario1 = Usuarioservice.adduser(user);
        URI location = URI.create("/usuario/"+usuario1.getId());
        UsuarioDTORespuesta usuarioDTORespuesta = UsuarioDTORespuesta.builder().nombre(usuario1.getNombre()).build();
        return ResponseEntity.created(location).body(usuarioDTORespuesta);
    }
}
