package org.iesch.ad.DocumentosReferenciados.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.iesch.ad.DocumentosReferenciados.service.BookRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books-ref/template")
public class BookRefTemplateController {
    @Autowired
    BookRefService bookRefService;
    //Crud

    /**
     * GET ALL
     */

    //Dependencia OpenAPI para documentar el endpoint en http://127.0.0.1:8080/swagger-ui/index.html
    @Operation(summary = "Obtener todos los libros de referencia",
            description = "Devuelve una lista de todos los libros de referencia disponibles en la base de datos. Si no hay libros, devuelve una respuesta sin contenido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de libros de referencia obtenida exitosamente"),
            @ApiResponse(responseCode = "204", description = "No se encontraron libros de referencia")
    })

    @GetMapping
    public ResponseEntity<List<BookRef>> getAllBooks() {
        List<BookRef> bookRefs = bookRefService.buscarTodos();
        if (bookRefs == null || bookRefs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookRefs);
    }


    /**
     * Buscar libros por titulo
     */
    @GetMapping("/search/titulo")
    public ResponseEntity<List<BookRef>> buscarPorTitulo(@RequestParam String q) {
        return ResponseEntity.ok(bookRefService.findByTituloContainingIgnoreCase(q));
    }
    /**
     * Buscar libros por el ID del autor
     */
    @GetMapping("/search/autor/{autorId}")
    public ResponseEntity<List<BookRef>> buscarPorAutorId(@PathVariable String autorId) {
        return ResponseEntity.ok(bookRefService.findByAutorId(autorId));
    }

//    @GetMapping{"/{id}"}
//    public ResponseEntity<List<BookRef>> getOne(@PathVariable String id) {
//        return ResponseEntity.ok(bookRefService.findByAutorId(id));
//    }
    /**
     * Buscar libros de autor especifico (por nombre) usar lookup
     */
    @GetMapping("/search/autor-nombre")
    public ResponseEntity<List<BookRef>> buscarPorNombreAutor(@RequestParam String nombre) {
        return ResponseEntity.ok(bookRefService.findByAutorNombre(nombre));
    }
}
