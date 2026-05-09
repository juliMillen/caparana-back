package com.jm.caparana.DTO;

import com.jm.caparana.Entity.Person;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PlayerDTO extends Person {
    private Long idPlayer;

    private String name;

    private String surname;

    private String position;

    private int num;

    private String urlImage;

    private CategorityDTO categority;


}
