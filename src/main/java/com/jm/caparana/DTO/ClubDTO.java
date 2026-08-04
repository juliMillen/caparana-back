package com.jm.caparana.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubDTO {

    private Long idClub;

    private String name;

    private LocalDate fundationDate;

    private String history;

    private String stadiumHistory;

    private String colorsHistory;

    private List<String> titles = new ArrayList<>();

    private String urlImageShield;

    private String urlImageStadium;
}
