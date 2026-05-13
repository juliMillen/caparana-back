package com.jm.caparana.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GalleryPhotoDTO {

    private Long idGallery;

    private String title;

    private LocalDate publicationDate;

    private List<PhotoDTO> photoDTOS;
}
