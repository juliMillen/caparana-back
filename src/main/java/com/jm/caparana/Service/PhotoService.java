package com.jm.caparana.Service;

import com.jm.caparana.DTO.PhotoDTO;
import com.jm.caparana.Entity.GalleryPhoto;
import com.jm.caparana.Entity.Photo;
import com.jm.caparana.Exception.GalleryException;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.IGalleryRepository;
import com.jm.caparana.Repository.IPhotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private IPhotoRepository photoRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private IGalleryRepository galleryRepository;


    public List<PhotoDTO> findAllPhotos(){
        return photoRepository.findAll().stream()
                .map(Mapper::mapToPhotoDTO)
                .toList();
    }

    public PhotoDTO findPhotoById(Long idPhoto){
        return Mapper.mapToPhotoDTO(photoRepository.findById(idPhoto).orElseThrow(()-> new RuntimeException("Photo not found")));
    }

    public PhotoDTO createPhoto(String description, MultipartFile image)throws IOException {
        String urlImage= cloudinaryService.uploadImage(image);

        Photo toCreate = Photo.builder()
                .description(description)
                .urlImage(urlImage)
                .build();
        return Mapper.mapToPhotoDTO(photoRepository.save(toCreate));
    }

    @Transactional
    public PhotoDTO addPhotoGallery(Long idGallery, String description, MultipartFile image) throws IOException{
        GalleryPhoto gallery = galleryRepository.findById(idGallery).orElseThrow(()-> new GalleryException("Gallery not found"));
        String urlImage = cloudinaryService.uploadImage(image);

        Photo toAdd = Photo.builder()
                .description(description)
                .urlImage(urlImage)
                .galleryPhoto(gallery)
                .build();
        return Mapper.mapToPhotoDTO(photoRepository.save(toAdd));
    }

    public void deletePhoto(Long id){
        if(id == null || id <= 0 ){
            throw new RuntimeException("id invalid");
        }
        photoRepository.deleteById(id);
    }
}
