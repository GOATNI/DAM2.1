package org.iesch.ad.Excepciones.Modelo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteRequestestPeticiondto {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2,max = 50,message = "El nombre debe tener entre 2 y 50 caracteres")
    private  String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2,max = 100,message = "El apellido debe tener entre 2 y 100 caracteres")
    private String apellidos;
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato valido")
    private String email;
    @NotNull(message = "La edad es obligatorio")
    @Min(value = 16, message = "La edad minima es 16 años")
    @Max(value = 99, message = "La edad maxima es 99 años")
    private Integer edad;
    @NotBlank(message = "El ciclo formativo es obligatorio")
    @Pattern(regexp = "DAM|DAW|ASIR|SMR",message = "El ciclo debe ser de informatica DAM|DAW|ASIR|SMR")
    private String ciclo;


}
