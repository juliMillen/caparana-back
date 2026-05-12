package com.jm.caparana.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String history;

    private String stadiumHistory;

    private String colorsHistory;

    private List<String> titulos = new ArrayList<>();

    private String urlImageShield;

    private String urlImageStadium;


}
