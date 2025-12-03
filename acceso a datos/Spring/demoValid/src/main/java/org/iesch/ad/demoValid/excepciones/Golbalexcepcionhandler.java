package org.iesch.ad.demoValid.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class Golbalexcepcionhandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponsedto> manjardatosErroneos(MethodArgumentNotValidException s){
        String mensaje ="Erro de validacion "+s.getBindingResult().getFieldError().getDefaultMessage();
       ErrorResponsedto errorResponsedto = new ErrorResponsedto(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(), mensaje);
       return new ResponseEntity<>(errorResponsedto,HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(Miexcepcion.c
//    +lass)
//    public ResponseEntity<ErrorResponsedto> tratandomierror(Miexcepcion ex){
//        ErrorResponsedto errorResponsedto = new ErrorResponsedto(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),ex.getMessage());
//
//        return new ResponseEntity<>(errorResponsedto,HttpStatus.BAD_REQUEST);
//
//
//
//    }

}
