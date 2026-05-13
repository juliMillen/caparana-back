package com.jm.caparana.Service;

import com.jm.caparana.DTO.GalleryPhotoDTO;
import com.jm.caparana.DTO.PhotoDTO;
import com.jm.caparana.Entity.GalleryPhoto;
import com.jm.caparana.Entity.Photo;
import com.jm.caparana.Exception.GalleryException;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.IGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryService {

    @Autowired
    private IGalleryRepository galleryRepository;

    public GalleryPhotoDTO create(GalleryPhotoDTO galleryDTO){

        List<Photo> photos = galleryDTO.getPhotoDTOS().stream()
                .map(photoDTO -> Photo.builder()
                        .idPhoto(photoDTO.getIdPhoto())
                        .description(photoDTO.getDescription())
                        .urlImage(photoDTO.getUrlImage())
                        .build())
                .collect(Collectors.toList());

        GalleryPhoto toCreate = GalleryPhoto.builder()
                .idGallery(galleryDTO.getIdGallery())
                .title(galleryDTO.getTitle())
                .publicationDate(galleryDTO.getPublicationDate())
                .photos(photos)
                .build();
        return Mapper.mapToGalleryDTO(galleryRepository.save(toCreate));
    }

    public GalleryPhotoDTO updateGallery(Long idGallery, GalleryPhotoDTO gallery){
        GalleryPhoto toUpdate = galleryRepository.findById(idGallery).orElseThrow(() -> new GalleryException("Gallery not found"));

        List<Photo> photos = gallery.getPhotoDTOS().stream()
                        .map(photoDTO -> Photo.builder()
                                .idPhoto(photoDTO.getIdPhoto())
                                .description(photoDTO.getDescription())
                                .urlImage(photoDTO.getUrlImage())
                                .build())
                                .collect(Collectors.toList());

        toUpdate.setTitle(gallery.getTitle());
        toUpdate.setPublicationDate(gallery.getPublicationDate());
        toUpdate.setPhotos(photos);
        return Mapper.mapToGalleryDTO(galleryRepository.save(toUpdate));
    }

    public void deleteGallery(Long idGallery){
        if(idGallery == null || idGallery <= 0){
            throw new RuntimeException("id invalid");
        }
        galleryRepository.deleteById(idGallery);
    }
}
