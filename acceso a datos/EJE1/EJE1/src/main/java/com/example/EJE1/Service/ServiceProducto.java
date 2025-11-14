package com.example.EJE1.Service;

import com.example.EJE1.modelo.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ServiceProducto {

    private static final Logger log = LoggerFactory.getLogger(ServiceProducto.class);

    private final Map<Long, Producto> productoMap;

    @Autowired
    public ServiceProducto(Map<Long, Producto> productoMap) {
        this.productoMap = productoMap;
    }

    public List<Producto> getallproductos() {
        log.info("Obteniendo todos los productos");
        return productoMap.values().stream().toList();
    }

    public Producto addProducto(Producto producto) {

        if (!validarStock(producto)) {
            log.error("Stock inválido al crear producto: {}", producto);
            return null;
        }

        aplicarDescuento(producto);

        if (!productoMap.containsKey(producto.getId())) {
            productoMap.put(producto.getId(), producto);
            log.info("Producto añadido correctamente: {}", producto);
            return producto;
        }

        log.warn("Intento de crear producto con ID duplicado: {}", producto.getId());
        return null;
    }

    public Producto updatedProducto(Long id, Producto producto) {

        if (!validarStock(producto)) {
            log.error("Stock inválido al actualizar producto: {}", producto);
            return null;
        }

        aplicarDescuento(producto);

        if (productoMap.containsKey(id)) {
            productoMap.replace(id, producto);
            log.info("Producto actualizado correctamente: {}", producto);
            return productoMap.get(id);
        }

        log.warn("Intento de actualizar producto inexistente ID: {}", id);
        return null;
    }

    public Producto deleteProducto(Long id) {

        if (productoMap.containsKey(id)) {
            Producto eliminado = productoMap.remove(id);
            log.info("Producto eliminado: {}", eliminado);
            return eliminado;
        }

        log.warn("Intento de eliminar producto inexistente ID: {}", id);
        return null;
    }

    // ------------------------
    // MÉTODOS DE REGLAS INTERNAS
    // ------------------------

    private boolean validarStock(Producto producto) {
        try {
            int stock = Integer.parseInt(producto.getStock());
            return stock >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void aplicarDescuento(Producto producto) {
        try {
            double precio = Double.parseDouble(producto.getPrice());

            if (producto.getCategory().equalsIgnoreCase("Electrónica") && precio > 100) {
                double nuevo = precio * 0.9;
                producto.setPrice(String.valueOf(nuevo));
                log.info("Descuento aplicado. Precio final: {}", nuevo);
            }
        } catch (NumberFormatException ignored) {}
    }
}
