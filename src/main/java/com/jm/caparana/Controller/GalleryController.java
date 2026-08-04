package com.jm.caparana.Controller;

import com.jm.caparana.DTO.GalleryPhotoDTO;
import com.jm.caparana.Entity.GalleryPhoto;
import com.jm.caparana.Service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @PostMapping("/create")
    public ResponseEntity<GalleryPhotoDTO> createGallery(@RequestBody GalleryPhotoDTO gallery){
        return new ResponseEntity<>(galleryService.create(gallery), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GalleryPhotoDTO> updateGallery(@PathVariable Long id,  @RequestBody GalleryPhotoDTO gallery){
        return new ResponseEntity<>(galleryService.updateGallery(id,gallery),HttpStatus.OK);
    }


    //THIS IS FOR TEST IN THE BACKEND
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteGallery(@PathVariable Long id){
        galleryService.deleteGallery(id);
        return new ResponseEntity<>("Gallery deleted succesfully", HttpStatus.NOT_FOUND);
    }
}
