package org.iesch.SegundoSpring.controler;


import org.iesch.SegundoSpring.modelo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProductosControler {
    @Autowired
    Map<Long,Product> products;
    @GetMapping("/producto")
    public ResponseEntity<?> gettodos(){
        if (products.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(products);
        }
    }
    @GetMapping ()
}
