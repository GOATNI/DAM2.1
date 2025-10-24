package org.iesch.SegundoSpring.controler;


import org.iesch.SegundoSpring.modelo.Product;
import org.iesch.SegundoSpring.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class ProductosControler {
    @Autowired
    Map<Long,Product> products;
    @Autowired
    ProductoService productoService;

    @GetMapping("/producto")
    public ResponseEntity<?> gettodos(){
        if (products.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(products);
        }
    }
    @GetMapping ("/producto/{id}")
    public ResponseEntity<?> getone(@PathVariable Long id){
        Product product = productoService.getone(id);
        if (products== null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(product);
        }


    }

    @PostMapping("/producto")
    public ResponseEntity<?> addOne(@RequestBody Product product){
        productoService.addOne(product);
        URI location = URI.create("/producto/"+product.getId());
        return ResponseEntity.created(location).body(product);
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<?> updateone(@RequestBody Product product,  @PathVariable Long id){
      Product productmp = productoService.updateone(product ,id);
      if (productmp == null){
          return ResponseEntity.notFound().build();
      }else
          return ResponseEntity.ok(productmp);
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<?> Deleteone(@PathVariable Long id){
        Product productmp = productoService.deleteone(id);
        if (productmp == null){
            return ResponseEntity.notFound().build();
        }else
            return ResponseEntity.noContent().build();
    }


}
