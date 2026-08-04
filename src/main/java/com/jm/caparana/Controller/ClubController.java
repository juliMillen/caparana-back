package com.jm.caparana.Controller;


import com.jm.caparana.DTO.ClubDTO;
import com.jm.caparana.Entity.Club;
import com.jm.caparana.Service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/club")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> getClubById(@PathVariable Long id){
        return new ResponseEntity<>(clubService.findClubById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClubDTO> createClub(@RequestBody ClubDTO club){
        return new ResponseEntity<>(clubService.save(club),HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClubDTO> updateClub(@PathVariable Long idClub,@RequestBody ClubDTO club){
        return new ResponseEntity<>(clubService.updateClub(idClub,club),HttpStatus.OK);
    }


    // THIS IS UNIQUE FOR TEST IN THE BACKEND
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteClub(@PathVariable Long id){
        clubService.deleteClub(id);
        return new ResponseEntity<>("Club deleted succesfully", HttpStatus.NOT_FOUND);
    }
}
