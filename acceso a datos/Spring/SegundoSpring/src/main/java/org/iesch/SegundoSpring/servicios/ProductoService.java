package org.iesch.SegundoSpring.servicios;

import lombok.Setter;
import org.iesch.SegundoSpring.modelo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class ProductoService {
    @Autowired
    Map<Long, Product> productos;

    public Product getone(Long id){
        Product p = productos.get(id);
        System.out.println(p);
        return p;
    }

    public void addOne(Product product) {
        Long maxkey = Collections.max(productos.keySet());
        product.setId(maxkey+1);
        productos.put(maxkey+1,product);

    }

    public Product updateone(Product product, Long id) {
        Product productmp = productos.get(id);
        if (productmp == null){
            return null;

        }else{
            product.setId(id);
            productos.put(id,product);
            return product;
        }


    }

    public Product deleteone(Long id) {
        Product productmp = productos.get(id);
        if (productmp == null){
            return null;

        }else{
            productos.remove(id);
            return productmp;
        }

    }
}
