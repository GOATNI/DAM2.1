package org.iesch.ad.dgt_api.model;

import org.iesch.ad.dgt_api.model.enums.EstadoVehiculo;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SituacionAdministrativa {
    private EstadoVehiculo estado; // Usamos tu Enum aquí
    private LocalDateTime fechaEstado;
    private String motivoBaja;
    private LocalDateTime fechaBajaTemporal;
    private LocalDateTime fechaFinBajaTemporal;
    private boolean precintado;
    private LocalDateTime fechaPrecinto;
    private String motivoPrecinto;
}