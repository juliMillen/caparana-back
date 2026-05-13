package com.jm.caparana.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Photo {

    private Long idPhoto;

    private String description;

    private String urlImage;

    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private GalleryPhoto galleryPhoto;
}
