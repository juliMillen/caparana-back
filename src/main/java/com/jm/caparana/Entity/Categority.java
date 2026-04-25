package com.jm.caparana.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategority;

    private String nameCategority;

    @OneToMany(mappedBy = "categority", cascade = CascadeType.ALL)
    private List<Player> playerList = new ArrayList<>();
}
