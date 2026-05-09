package com.jm.caparana.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDTO {

    private Long idReport;

    private String title;

    private String description;

    private LocalDate publicationDate;

    private String urlImage;
}
