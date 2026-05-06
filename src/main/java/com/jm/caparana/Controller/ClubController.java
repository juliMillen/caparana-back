package com.jm.caparana.Controller;


import com.jm.caparana.Entity.Club;
import com.jm.caparana.Service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/club")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id){
        return new ResponseEntity<>(clubService.findClubById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Club> createClub(@RequestBody Club club){
        return new ResponseEntity<>(clubService.save(club),HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<Club> updateClub(@RequestBody Club club){
        return new ResponseEntity<>(clubService.updateClub(club),HttpStatus.OK);
    }


    // THIS IS UNIQUE FOR TEST IN THE BACKEND
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteClub(@PathVariable Long id){
        clubService.deleteClub(id);
        return new ResponseEntity<>("Club deleted succesfully", HttpStatus.NOT_FOUND);
    }
}
