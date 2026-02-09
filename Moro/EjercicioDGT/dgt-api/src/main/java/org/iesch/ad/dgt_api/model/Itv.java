package org.iesch.ad.dgt_api.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Itv {
    private boolean enVigor;
    private LocalDateTime fechaUltimaInspeccion;
    private LocalDateTime fechaCaducidad;
    private String resultado;
    private String numeroInforme;
    private String estacionITV;
}