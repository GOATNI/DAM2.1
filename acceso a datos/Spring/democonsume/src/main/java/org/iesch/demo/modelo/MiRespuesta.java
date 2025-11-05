package org.iesch.demo.modelo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MiRespuesta {
    private List<Fact> factlist = new ArrayList<>();

    public void añadeFact(Fact fact){
        this.factlist.add(fact);
    }
}
