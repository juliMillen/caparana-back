package com.jm.caparana.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Executive extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExecutive;

    private String name;

    private String surname;

    private String position;

    private String urlImage;

}
