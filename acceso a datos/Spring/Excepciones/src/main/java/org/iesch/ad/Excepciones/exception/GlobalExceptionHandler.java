package org.iesch.ad.Excepciones.exception;

import org.iesch.ad.Excepciones.Modelo.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepcion cuando no se encuentra un estudiante
     * Devuelve un 404 not found
     */

    @ExceptionHandler(EstudianteNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> manejarEstudianteNoEncontrado(
            EstudianteNoEncontradoException ex, WebRequest request
    ){
        ErrorResponseDTO error = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=","")
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    /**
     * maneja la excepcion cuando hay email duplicado
     * nos va a devolver el codigo de estado http 409 conflict
     * */
    @ExceptionHandler(EmailDuplicadoexception.class)
    public ResponseEntity<ErrorResponseDTO> manejarEmailDuplicado(
            EmailDuplicadoexception ex, WebRequest request
    ){
        ErrorResponseDTO error = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                request.getDescription(false).replace("uri=","")
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
    /*
    *maneja las excepciones de datos invalidos
    *captura todas las validaciones que fallan y devuelve un error 400 bad request
    */

    @ExceptionHandler (DatosInvalidosException.class)
    public ResponseEntity<ErrorResponseDTO> manejarDatosinvalidos(
            DatosInvalidosException ex,WebRequest request
    ){
        ErrorResponseDTO error = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST",
                ex.getMessage(),
                request.getDescription(false).replace("uri=","")
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /*excepiciones generadas por @valid
    *que se metan en la lista
    * devolver 400 bad request
    *
     */

   /* @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> manejarvalidacion(
            MethodArgumentNotValidException ex,WebRequest request
    ){
        List<String> Detalles = new ArrayList<>();

        for (FieldError error:ex.getBindingResult().getFieldError()){
            Detalles.add(error.getField()+" "+error.getDefaultMessage());
        }
        ErrorResponseDTO error = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST",
                ex.getMessage(),
                request.getDescription(false).replace("uri=","")
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }*/


}
