package com.jm.caparana.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReport;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate publicationDate;

    private String urlImage;
}
