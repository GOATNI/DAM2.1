package org.iesch.ad.dgt_api.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Impuestos {
    private boolean itmvPagado;
    private LocalDateTime fechaUltimoPagoITMV;
    private Double importeITMV;
    private Integer anioITMV;
}