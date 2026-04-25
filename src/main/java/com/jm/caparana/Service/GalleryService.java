package com.jm.caparana.Service;

import com.jm.caparana.Entity.GalleryPhoto;
import com.jm.caparana.Repository.IGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {

    @Autowired
    private IGalleryRepository galleryRepository;

    public GalleryPhoto create(GalleryPhoto gallery){
        return galleryRepository.save(gallery);
    }

    public GalleryPhoto updateGallery(Long idGallery, GalleryPhoto gallery){
        GalleryPhoto toUpdate = galleryRepository.findById(idGallery).orElseThrow(() -> new RuntimeException("gallery not found"));
        toUpdate.setTitle(gallery.getTitle());
        toUpdate.setPublicationDate(gallery.getPublicationDate());
        toUpdate.setUrlImage(gallery.getUrlImage());
        galleryRepository.save(toUpdate);
        return toUpdate;
    }

    public void deleteGallery(Long idGallery){
        if(idGallery == null || idGallery <= 0){
            throw new RuntimeException("id invalid");
        }
        galleryRepository.deleteById(idGallery);
    }
}
