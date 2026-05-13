package com.jm.caparana.DTO;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoDTO {

    private Long idPhoto;

    private String description;

    private String urlImage;


}
