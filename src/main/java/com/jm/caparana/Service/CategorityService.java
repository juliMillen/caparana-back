package com.jm.caparana.Service;

import com.jm.caparana.DTO.CategorityDTO;
import com.jm.caparana.Entity.Categority;
import com.jm.caparana.Entity.Player;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.ICategorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CategorityService {

    @Autowired
    private ICategorityRepository categorityRepository;

    public List<CategorityDTO> findAllCategorities(){
        return categorityRepository.findAll().stream()
                .map(Mapper::mapToCategorityDTO)
                .toList();
    }

    public CategorityDTO findCategorityById(Long idCategority){
        if(idCategority == null || idCategority <= 0){
            throw new RuntimeException("id invalid");
        }
        return Mapper.mapToCategorityDTO(categorityRepository.findById(idCategority).orElseThrow(() -> new RuntimeException("categority not found")));
    }

    public CategorityDTO save(CategorityDTO categorityDTO){

        List<Player> players = categorityDTO.getPlayerList().stream()
                .map(playerDTO -> Player.builder()
                        .idPlayer(playerDTO.getIdPlayer())
                        .name(playerDTO.getName())
                        .surname(playerDTO.getSurname())
                        .position(playerDTO.getPosition())
                        .urlImagen(playerDTO.getUrlImage())
                        .build())
                .collect(Collectors.toList());

        Categority toCreate = Categority.builder()
                .nameCategority(categorityDTO.getNameCategority())
                .playerList(players)
                .build();

        return Mapper.mapToCategorityDTO(categorityRepository.save(toCreate));
    }

    public CategorityDTO updateCategority(Long idCategority, CategorityDTO categorityDTO){
        List<Player> players = categorityDTO.getPlayerList().stream()
                .map(playerDTO -> Player.builder()
                        .idPlayer(playerDTO.getIdPlayer())
                        .name(playerDTO.getName())
                        .surname(playerDTO.getSurname())
                        .position(playerDTO.getPosition())
                        .urlImagen(playerDTO.getUrlImage())
                        .build())
                .collect(Collectors.toList());

        Categority toUpdate = categorityRepository.findById(idCategority).orElseThrow(() -> new RuntimeException("Categority not found"));
        toUpdate.setNameCategority(categorityDTO.getNameCategority());
        toUpdate.setPlayerList(players);
        return Mapper.mapToCategorityDTO(categorityRepository.save(toUpdate));

    }

    public void deleteCategority(Long idCategority){
        if(idCategority == null || idCategority <= 0){
            throw new RuntimeException("id invalid");
        }
        categorityRepository.deleteById(idCategority);
    }
}
