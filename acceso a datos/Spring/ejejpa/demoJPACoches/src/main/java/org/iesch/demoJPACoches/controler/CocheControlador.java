package org.iesch.demoJPACoches.controler;

import org.iesch.demoJPACoches.modelo.coche;
import org.iesch.demoJPACoches.service.CocheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coche")
public class CocheControlador {
    @Autowired
    CocheService cocheService;

    @PostMapping
    public ResponseEntity<coche> guardar(@RequestBody coche coche){
        coche cochguardado = cocheService.guaradrcoche(coche);
        return ResponseEntity.ok(cochguardado);
    }

    @GetMapping
    public ResponseEntity<List<coche>> obtenertodos(){

        return ResponseEntity.ok(cocheService.obtenertodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<coche> obtener1(@PathVariable Long id){

        return ResponseEntity.ok(cocheService.obtener1(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<coche> uptade1(@PathVariable Long id,@RequestBody coche coche1){

        return ResponseEntity.ok(cocheService.update1(id,coche1));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<coche> delete1(@PathVariable Long id){
        coche cocheborrado = cocheService.delete1(id);
        if (cocheborrado != null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarpor/{color}")
    public  ResponseEntity<List<coche>> porcolor(@PathVariable String color){

        return ResponseEntity.ok(cocheService.cochesporcolor(color));

    }
    @GetMapping("/buscarpor/{color}/{marca}")
    public  ResponseEntity<List<coche>> porcolor(@PathVariable String color, @PathVariable String marca ){

        return ResponseEntity.ok(cocheService.cochesporcolorymarca(color,marca));

    }


}
