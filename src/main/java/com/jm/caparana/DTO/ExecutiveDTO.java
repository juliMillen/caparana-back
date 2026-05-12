package com.jm.caparana.DTO;

import com.jm.caparana.Entity.Person;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExecutiveDTO extends Person {

    private Long idExecutive;

    private String name;

    private String surname;

    private String position;

    private String urlImage;
}
