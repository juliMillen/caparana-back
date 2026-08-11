package com.jm.caparana.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DisciplineDTO {

    private Long idDiscipline;

    private String nameDiscipline;

    private String description;

    private LocalTime schedule;

    private String professorAsig;

    private String ubication;
}
