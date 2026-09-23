package com.jm.caparana.Controller;

import com.jm.caparana.DTO.PlayerDTO;
import com.jm.caparana.Entity.Player;
import com.jm.caparana.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<PlayerDTO> createPlayer(@PathVariable Long idCategority, @RequestParam("name") String name,
                                                  @RequestParam("surname") String surname, @RequestParam("position")String position,
                                                  @RequestParam("num") int num, @RequestParam("image") MultipartFile image) throws IOException {
        return new ResponseEntity<>(playerService.save(idCategority,name,surname,position,num,image),HttpStatus.CREATED);
    }

    @PatchMapping("/update/{idPlayer}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long idPlayer,@RequestParam("name")String name, @RequestParam("surname")String surname,
                                                  @RequestParam("position")String position, @RequestParam("num")int num, @RequestParam(value="image", required = false)MultipartFile image)throws IOException{
        return new ResponseEntity<>(playerService.updatePlayer(idPlayer,name,surname,position,num,image), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        return new ResponseEntity<>("Player deleted succesfully", HttpStatus.NOT_FOUND);
    }
}
