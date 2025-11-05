package org.iesch.demo.modelo;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Fact {
    private String fact;
    private int leanth = fact.length();
}
