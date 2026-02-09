package org.iesch.ad.dgt_api.model;

import org.iesch.ad.dgt_api.model.enums.EstadoMulta;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Multa {
    private String id = UUID.randomUUID().toString();
    private String concepto;
    private Double importe;
    private Integer puntos;
    private LocalDateTime fecha;
    private String lugarInfraccion;
    private String agente;
    private EstadoMulta estado; // Usamos tu Enum aquí
    private LocalDateTime fechaPago;
    private String metodoPago;
}