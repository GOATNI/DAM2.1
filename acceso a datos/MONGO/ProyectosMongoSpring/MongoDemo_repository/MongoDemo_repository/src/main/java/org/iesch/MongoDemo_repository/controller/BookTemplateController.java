package org.iesch.MongoDemo_repository.controller;

import org.iesch.MongoDemo_repository.model.Book;
import org.iesch.MongoDemo_repository.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping("/api/books/template")
public class BookTemplateController {

    @Autowired
    BookService bookService;

    //==========Consultas Básicas==========


    //Get /api/books/template/search/titulo?q=titulo
    //Buscar libros por título

    @GetMapping("/search/titulo")
    public ResponseEntity<List<Book>> buscarPorTitulo(@RequestParam String q){
        return ResponseEntity.ok(bookService.findByTituloContainingIgnoreCase(q));
    }

    //Buscar por categoria
    //Get api/books/template/search/categorias?cat=
    @GetMapping("/search/categorias")
    public ResponseEntity<List<Book>> buscarPorCategoria(@RequestParam String cat){
        return ResponseEntity.ok(bookService.findByCategoriaIgnoreCase(cat));
    }

    //Buscar por Autor
    //Get api/books/template/search/autores?nombre=
    @GetMapping("/search/autores")
    public ResponseEntity<List<Book>> buscarPorNombreAutor(@RequestParam String nombre){
        return ResponseEntity.ok(bookService.findByAutor(nombre));
    }

    //Buscar libros por un rango de precios
    //Get api/books/template/search/precio?min=X&max=Y

    @GetMapping("/search/precio")
    public ResponseEntity<List<Book>> buscarPorRangoDePrecio(@RequestParam Double min,@RequestParam Double max){
        return ResponseEntity.ok(bookService.findByPrecioBetween(min,max));
    }

    //Get api/books/template/search/recientes?anio=
    //Buscar libros mas recientes a un anio

    @GetMapping("/search/recientes")
    public ResponseEntity<List<Book>> buscaLibrosRecientesAUnAnio(@RequestParam Integer anio){
        return ResponseEntity.ok(bookService.findByAioPublicacionReciente(anio));
    }

    //Get api/books/template/search/precio/anio?precio=&anio=
    //Buscar libros con un precio inferior a x y que se hayan publicado despues del año x

    @GetMapping("/search/precio/anio")
    public ResponseEntity<List<Book>> buscarLibrosPorPrecioYanio(@RequestParam Double precio, @RequestParam Integer anio){
        return ResponseEntity.ok(bookService.findByPrecioAnio(precio,anio));
    }
    // buscar libros por titulo y categoria titulo string regex categorias string[]
    // ruta api/books/template/search/titulo/categoria

    @GetMapping("/search/titulo/categoria")
    public ResponseEntity<List<Book>> buscarLibrosPorTituloCategoria(@RequestParam String titulo, @RequestParam String categoria){
        return ResponseEntity.ok(bookService.findByTitulocategoria(titulo,categoria));
    }
    //mandar un array
    @PostMapping("/serch/categorias-multiples")
    public ResponseEntity<List<Book>> Buscarpormultiplescategorias(@RequestBody List<String> categorias){
        return ResponseEntity.ok(bookService.buscarPorCategorias(categorias));
    }
    /****
     *
     * Buscar por libros por precio inferir que me pasan y ordenar por año publicacion
     *
     */
    @GetMapping("/search/precio-maximo-ordenado")
    public ResponseEntity<List<Book>> buscarporPrecioMaximo(@RequestParam double precio){
        return ResponseEntity.ok(bookService.preciomaximoordenado(precio));
    }
    /*****
     * Libros con multiples autores
     */
    @GetMapping("/search/multiples-autores")
    public ResponseEntity<List<Book>> bucarlibrosconmultipleautores(){
        return ResponseEntity.ok(bookService.buscrapormultiplesautores());
    }
    /***
     * contar libros por categoria
     */
    @GetMapping("/search/contar-categoria")
    public ResponseEntity<Long> buscarcantidadlibroporcategias(@RequestParam String categoria){
        return ResponseEntity.ok(bookService.buscarcantidadlibroporcategias(categoria));
    }

}
