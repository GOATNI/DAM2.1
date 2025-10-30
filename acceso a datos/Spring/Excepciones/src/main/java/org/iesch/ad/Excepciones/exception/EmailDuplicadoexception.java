package org.iesch.ad.Excepciones.exception;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailDuplicadoexception extends RuntimeException {
    public EmailDuplicadoexception(String email) {
        super("El email "+email+ " ya esta registrado");
    }
}
