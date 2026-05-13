package com.jm.caparana.DTO;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SponsorDTO {

    private Long idSponsor;

    private String name;

    private String urlImage;
}
