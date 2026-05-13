package com.jm.caparana.Controller;

import com.jm.caparana.DTO.SponsorDTO;
import com.jm.caparana.Service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sponsor")
public class SponsorController {

    @Autowired
    private SponsorService sponsorService;

    @GetMapping("")
    public ResponseEntity<List<SponsorDTO>> findAllSponsors(){
        List<SponsorDTO> sponsorList = sponsorService.finAllSponsors();
        return new ResponseEntity<>(sponsorList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SponsorDTO> getSponsorById(@PathVariable Long id){
        return new ResponseEntity<>(sponsorService.findSponsorByID(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SponsorDTO> createSponsor(@RequestBody SponsorDTO sponsor){
        return new ResponseEntity<>(sponsorService.createSponsor(sponsor),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSponsor(@PathVariable Long id){
        sponsorService.deleteSponsor(id);
        return new ResponseEntity<>("Sponsor deleted succesfully",HttpStatus.NOT_FOUND);
    }
}
