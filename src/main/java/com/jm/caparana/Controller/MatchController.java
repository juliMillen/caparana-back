package com.jm.caparana.Controller;

import com.jm.caparana.DTO.MatchDTO;
import com.jm.caparana.Service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("")
    public ResponseEntity<List<MatchDTO>> findAll(){
        List<MatchDTO> listM = matchService.findAllMatches();
        return new ResponseEntity<>(listM, HttpStatus.OK);
    }

    @GetMapping("/{idMatch}")
    public ResponseEntity<MatchDTO> findById(@PathVariable Long idMatch){
        return new ResponseEntity<>(matchService.findMatchById(idMatch), HttpStatus.OK);
    }


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<MatchDTO> save(@RequestBody MatchDTO dto){
        return new ResponseEntity<>(matchService.save(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{idMatch}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<MatchDTO> updateMatch(@PathVariable Long idMatch, @RequestBody MatchDTO match){
        return new ResponseEntity<>(matchService.updateMatch(idMatch,match),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idMatch}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<String> deleteMatch(@PathVariable Long idMatch){
        matchService.deleteMatch(idMatch);
        return new ResponseEntity<>("Match deleted succesfully",HttpStatus.NO_CONTENT);
    }


}
