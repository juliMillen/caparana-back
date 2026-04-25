package com.jm.caparana.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlayer;

    private int num;

    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategority")
    private Categority categority;

}
