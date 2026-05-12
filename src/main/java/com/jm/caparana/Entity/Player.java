package com.jm.caparana.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlayer;

    private String name;

    private String surname;

    private int num;

    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategority")
    private Categority categority;

    private String urlImagen;

}
