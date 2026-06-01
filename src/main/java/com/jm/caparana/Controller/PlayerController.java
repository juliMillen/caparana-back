package com.jm.caparana.Controller;

import com.jm.caparana.DTO.PlayerDTO;
import com.jm.caparana.Entity.Player;
import com.jm.caparana.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers(){
        List<PlayerDTO> playerList = playerService.findAllPlayers();
        return new ResponseEntity<>(playerList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id){
        return new ResponseEntity<>(playerService.findPlayerById(id),HttpStatus.OK);
    }

    @PostMapping("/create/{idCategority}")
    @PreAuthorize("hasRole('ADMIN)")
    public ResponseEntity<PlayerDTO> createPlayer(@PathVariable Long idCategority, @RequestBody PlayerDTO player){
        return new ResponseEntity<>(playerService.save(idCategority,player),HttpStatus.CREATED);
    }

    @PatchMapping("/update/{idPlayer}")
    @PreAuthorize("hasRole('ADMIN)")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long idPlayer,@RequestBody PlayerDTO player){
        return new ResponseEntity<>(playerService.updatePlayer(idPlayer,player), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN)")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        return new ResponseEntity<>("Player deleted succesfully", HttpStatus.NOT_FOUND);
    }
}
