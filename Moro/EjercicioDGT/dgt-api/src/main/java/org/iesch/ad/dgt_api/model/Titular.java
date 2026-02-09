package org.iesch.ad.dgt_api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Titular {
    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{8}[A-Z]", message = "El DNI debe tener 8 números y una letra mayúscula")
    private String dni;

    private String nombre;
    private String apellidos;
    private LocalDateTime fechaNacimiento;

    @Email(message = "El formato del email no es válido")
    private String email;
    private String telefono;

    private Domicilio domicilioFiscal;
}