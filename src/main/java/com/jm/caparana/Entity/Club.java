package com.jm.caparana.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClub;

    private String name;

    private LocalDate fundationDate;

    @Column(columnDefinition = "TEXT")
    private String history;

    @Column(columnDefinition = "TEXT")
    private String stadiumHistory;

    @Column(columnDefinition = "TEXT")
    private String colorsHistory;

    private List<String> titles = new ArrayList<>();

    private String urlImageShield;

    private String urlImageStadium;


}
