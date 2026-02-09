package org.iesch.ad.dgt_api.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HistorialTitular {
    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String motivoTransferencia;
}