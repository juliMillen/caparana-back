package com.jm.caparana.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhoto;

    private String description;

    private String urlImage;

    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private GalleryPhoto galleryPhoto;
}
