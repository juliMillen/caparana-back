package com.jm.caparana.Controller;


import com.jm.caparana.DTO.ClubDTO;
import com.jm.caparana.Entity.Club;
import com.jm.caparana.Service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<ClubDTO> createClub(@RequestParam("name") String name, @RequestParam("fundationDate")LocalDate fundationDate,
                                              @RequestParam("history")String history, @RequestParam("stadiumHistory")String stadiumHistory,
                                              @RequestParam("colorsHistory")String colorsHistory,@RequestParam("titles") List<String> titles,
                                              @RequestParam("imageStadium")MultipartFile imageStadium,
                                              @RequestParam("imageShield") MultipartFile imageShield) throws IOException
    {
        return new ResponseEntity<>(clubService.save(name,fundationDate,history,stadiumHistory,colorsHistory,titles,imageStadium,imageShield),HttpStatus.CREATED);
    }

    @PatchMapping("/update/{idClub}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ClubDTO> updateClub(@PathVariable Long idClub,@RequestParam("name") String name, @RequestParam("fundationDate")LocalDate fundationDate,
                                              @RequestParam("history")String history, @RequestParam("stadiumHistory")String stadiumHistory,
                                              @RequestParam("colorsHistory")String colorsHistory,@RequestParam(value = "titles",required = false) List<String> titles,
                                              @RequestParam(value = "imageStadium", required = false)MultipartFile imageStadium,
                                              @RequestParam(value = "imageShield", required = false) MultipartFile imageShield) throws IOException{
        return new ResponseEntity<>(clubService.updateClub(idClub,name,fundationDate,history,stadiumHistory,colorsHistory,titles,imageStadium,imageShield),HttpStatus.OK);
    }


    // THIS IS UNIQUE FOR TEST IN THE BACKEND
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<String> deleteClub(@PathVariable Long id){
        clubService.deleteClub(id);
        return new ResponseEntity<>("Club deleted succesfully", HttpStatus.OK);
    }
}
