package com.jm.caparana.Service;

import com.jm.caparana.DTO.GalleryPhotoDTO;
import com.jm.caparana.Entity.GalleryPhoto;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.IGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

    @Autowired
    private IGalleryRepository galleryRepository;

    public GalleryPhotoDTO create(GalleryPhotoDTO galleryDTO){
        GalleryPhoto toCreate = GalleryPhoto.builder()
                .idGallery(galleryDTO.getIdGallery())
                .title(galleryDTO.getTitle())
                .publicationDate(galleryDTO.getPublicationDate())
                .urlImage(galleryDTO.getUrlImage())
                .build();
        return Mapper.mapToGalleryDTO(galleryRepository.save(toCreate));
    }

    public GalleryPhotoDTO updateGallery(Long idGallery, GalleryPhotoDTO gallery){
        GalleryPhoto toUpdate = galleryRepository.findById(idGallery).orElseThrow(() -> new RuntimeException("Gallery not found"));
        toUpdate.setTitle(gallery.getTitle());
        toUpdate.setPublicationDate(gallery.getPublicationDate());
        toUpdate.setUrlImage(gallery.getUrlImage());
        return Mapper.mapToGalleryDTO(galleryRepository.save(toUpdate));
    }

    public void deleteGallery(Long idGallery){
        if(idGallery == null || idGallery <= 0){
            throw new RuntimeException("id invalid");
        }
        galleryRepository.deleteById(idGallery);
    }
}
