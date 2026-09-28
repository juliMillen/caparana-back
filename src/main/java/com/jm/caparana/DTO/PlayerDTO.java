package com.jm.caparana.DTO;

import com.jm.caparana.Entity.Person;
import com.jm.caparana.Enum.PlayerPosition;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@SuperBuilder
public class PlayerDTO extends Person {
    private Long idPlayer;

    private int num;

    private PlayerPosition position;

}
