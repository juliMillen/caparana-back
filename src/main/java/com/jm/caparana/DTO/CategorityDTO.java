package com.jm.caparana.DTO;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategorityDTO {

    private Long idCategority;

    private String nameCategority;

    private List<PlayerDTO> playerList = new ArrayList<>();
}
