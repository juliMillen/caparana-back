package com.jm.caparana.Service;

import com.jm.caparana.DTO.PhotoDTO;
import com.jm.caparana.Entity.Photo;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.IPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private IPhotoRepository photoRepository;


    public List<PhotoDTO> findAllPhotos(){
        return photoRepository.findAll().stream()
                .map(Mapper::mapToPhotoDTO)
                .toList();
    }

    public PhotoDTO findPhotoById(Long idPhoto){
        return Mapper.mapToPhotoDTO(photoRepository.findById(idPhoto).orElseThrow(()-> new RuntimeException("Photo not found")));
    }

    public PhotoDTO createPhoto(PhotoDTO photoDTO){
        Photo toCreate = Photo.builder()
                .description(photoDTO.getDescription())
                .urlImage(photoDTO.getUrlImage())
                .build();
        return Mapper.mapToPhotoDTO(photoRepository.save(toCreate));
    }

    public void deletePhoto(Long id){
        if(id == null || id <= 0 ){
            throw new RuntimeException("id invalid");
        }
        photoRepository.deleteById(id);
    }
}
