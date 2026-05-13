package com.jm.caparana.Service;

import com.jm.caparana.DTO.PlayerDTO;
import com.jm.caparana.Entity.Categority;
import com.jm.caparana.Entity.Player;
import com.jm.caparana.Exception.PlayerException;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.ICategorityRepository;
import com.jm.caparana.Repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private IPlayerRepository playerRepository;

    @Autowired
    private ICategorityRepository categorityRepository;

    public List<PlayerDTO> findAllPlayers(){
        return playerRepository.findAll().stream()
                .map(Mapper::mapToPlayerDTO)
                .toList();
    }

    public PlayerDTO findPlayerById(Long idPlayer){
        if(idPlayer == null || idPlayer <= 0){
            throw new RuntimeException("id invalid");
        }
        return Mapper.mapToPlayerDTO(playerRepository.findById(idPlayer).orElseThrow(() -> new PlayerException("player not found")));
    }

    public PlayerDTO save(Long idCategority,PlayerDTO playerDTO){

        Categority categority = Categority.builder()
                .idCategority(playerDTO.getCategority().getIdCategority())
                .nameCategority(playerDTO.getCategority().getNameCategority())
                .build();

        Player toCreate = Player.builder()
                .name(playerDTO.getName())
                .surname(playerDTO.getSurname())
                .position(playerDTO.getPosition())
                .num(playerDTO.getNum())
                .urlImagen(playerDTO.getUrlImage())
                .categority(categority)
                .build();

        return Mapper.mapToPlayerDTO(playerRepository.save(toCreate));
    }

    public PlayerDTO updatePlayer(Long idPlayer, PlayerDTO playerDTO){

        Categority categority = Categority.builder()
                .idCategority(playerDTO.getCategority().getIdCategority())
                .nameCategority(playerDTO.getCategority().getNameCategority())
                .build();

        Player toUpdate = playerRepository.findById(idPlayer).orElseThrow(() -> new PlayerException("Player not found"));
        toUpdate.setName(playerDTO.getName());
        toUpdate.setSurname(playerDTO.getSurname());
        toUpdate.setPosition(playerDTO.getPosition());
        toUpdate.setNum(playerDTO.getNum());
        toUpdate.setUrlImagen(playerDTO.getUrlImage());
        toUpdate.setCategority(categority);
        return Mapper.mapToPlayerDTO(playerRepository.save(toUpdate));

    }

    public void deletePlayer(Long idPlayer){
        if(idPlayer == null || idPlayer <= 0){
            throw new RuntimeException("id is invalid");
        }
        playerRepository.deleteById(idPlayer);
    }
}
