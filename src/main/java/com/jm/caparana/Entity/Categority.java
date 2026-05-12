package com.jm.caparana.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategority;

    private String nameCategority;

    @OneToMany(mappedBy = "categority", cascade = CascadeType.ALL)
    private List<Player> playerList = new ArrayList<>();
}
