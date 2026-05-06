package com.jm.caparana.Controller;

import com.jm.caparana.Entity.GalleryPhoto;
import com.jm.caparana.Service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @PostMapping("/create")
    public ResponseEntity<GalleryPhoto> createGallery(@RequestBody GalleryPhoto gallery){
        return new ResponseEntity<>(galleryService.create(gallery), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<GalleryPhoto> updateGallery(@PathVariable Long id,  @RequestBody GalleryPhoto gallery){
        return new ResponseEntity<>(galleryService.updateGallery(id,gallery),HttpStatus.OK);
    }


    //THIS IS FOR TEST IN THE BACKEND
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGallery(@PathVariable Long id){
        galleryService.deleteGallery(id);
        return new ResponseEntity<>("Gallery deleted succesfully", HttpStatus.NOT_FOUND);
    }
}
