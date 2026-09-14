package com.jm.caparana.Controller;

import com.jm.caparana.DTO.PhotoDTO;
import com.jm.caparana.Service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping("")
    public ResponseEntity<List<PhotoDTO>> getPhotos(){
        List<PhotoDTO> photos = photoService.findAllPhotos();
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDTO> getPhotoById(@PathVariable Long id){
        return new ResponseEntity<>(photoService.findPhotoById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<PhotoDTO> createPhoto(@RequestParam("description") String description,
                                                @RequestParam("image")MultipartFile image)throws IOException {
        return new ResponseEntity<>(photoService.createPhoto(description,image),HttpStatus.CREATED);
    }

    @PostMapping("/gallery/{idGallery}")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<PhotoDTO> addPhotoGallery(@PathVariable Long idGallery, @RequestParam("description") String description,
                                                    @RequestParam("image") MultipartFile image)throws IOException{
        return new ResponseEntity<>(photoService.addPhotoGallery(idGallery,description,image),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<String> deletePhoto(@PathVariable Long id){
        photoService.deletePhoto(id);
        return new ResponseEntity<>("Photo deleted succesfully", HttpStatus.NOT_FOUND);
    }
}
