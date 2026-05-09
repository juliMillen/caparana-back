package com.jm.caparana.DTO;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubDTO {

    private Long idClub;

    private String name;

    private String history;

    private String stadiumHistory;

    private String colorsHistory;

    private List<String> titulos = new ArrayList<>();
}
