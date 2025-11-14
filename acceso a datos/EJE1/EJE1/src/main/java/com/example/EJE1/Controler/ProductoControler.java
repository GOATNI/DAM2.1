package com.example.EJE1.Controler;

import com.example.EJE1.Service.ServiceProducto;
import com.example.EJE1.modelo.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tienda")
public class ProductoControler {

    private static final Logger log = LoggerFactory.getLogger(ProductoControler.class);

    @Autowired
    ServiceProducto serviceproducto;

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> productos() {
        log.info("Solicitando lista de productos");
        return ResponseEntity.ok(serviceproducto.getallproductos());
    }

    @PostMapping("/productos")
    public ResponseEntity<Producto> addproductos(@RequestBody Producto producto) {
        log.info("Añadiendo producto: {}", producto);
        Producto nuevo = serviceproducto.addProducto(producto);

        if (nuevo == null) {
            log.warn("Error al crear producto con ID duplicado: {}", producto.getId());
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(nuevo);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        log.info("Actualizando producto con ID {}", id);
        Producto actualizado = serviceproducto.updatedProducto(id, producto);

        if (actualizado == null) {
            log.warn("Producto con ID {} no encontrado", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        log.info("Eliminando producto con ID {}", id);
        Producto eliminado = serviceproducto.deleteProducto(id);

        if (eliminado == null) {
            log.warn("No se pudo eliminar. Producto con ID {} no encontrado", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Producto eliminado correctamente");
    }
}
