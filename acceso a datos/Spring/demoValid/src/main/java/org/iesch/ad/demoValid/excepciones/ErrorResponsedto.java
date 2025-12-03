package org.iesch.ad.demoValid.excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ErrorResponsedto {

    private LocalDateTime timestamp;
    private int status;
    private String message;



}
