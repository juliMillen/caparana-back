package com.jm.caparana.Entity;

import com.jm.caparana.Enum.PlayerPosition;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Player extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlayer;

    private int num;

    private PlayerPosition position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategority")
    private Categority categority;

}
