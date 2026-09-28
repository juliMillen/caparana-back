package com.jm.caparana.DTO;

import com.jm.caparana.Entity.Person;
import com.jm.caparana.Enum.ExecutivePosition;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ExecutiveDTO extends Person {

    private Long idExecutive;

    private ExecutivePosition position;

}
