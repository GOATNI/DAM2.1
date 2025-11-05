package org.iesch.demo.controlador;

import org.iesch.demo.modelo.MiRespuesta;
import org.iesch.demo.service.TiendaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TiendaControler {
    @Autowired
    TiendaServicio tiendaServicio;
    @GetMapping("/obtener/{veses}")
    public ResponseEntity<MiRespuesta> obtener(@PathVariable int veses){
        MiRespuesta miRespuesta = tiendaServicio.obtener(veses);
        return ResponseEntity.ok(miRespuesta);

    }
}
