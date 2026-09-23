package com.jm.caparana.Service;

import com.jm.caparana.DTO.PlayerDTO;
import com.jm.caparana.Entity.Categority;
import com.jm.caparana.Entity.Player;
import com.jm.caparana.Exception.CategorityException;
import com.jm.caparana.Exception.PlayerException;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.ICategorityRepository;
import com.jm.caparana.Repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private IPlayerRepository playerRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

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

    public PlayerDTO save(Long idCategority, String name, String surname, String position, int num, MultipartFile image)throws IOException {
        String urlImage = cloudinaryService.uploadImage(image);
        Categority categority = categorityRepository.findById(idCategority).orElseThrow(()-> new CategorityException("Categority not found"));

        Player toCreate = Player.builder()
                .name(name)
                .surname(surname)
                .position(position)
                .num(num)
                .urlImage(urlImage)
                .categority(categority)
                .build();

        return Mapper.mapToPlayerDTO(playerRepository.save(toCreate));
    }

    public PlayerDTO updatePlayer(Long idPlayer, String name, String surname, String position, int num, MultipartFile image)throws IOException{

        Player toUpdate = playerRepository.findById(idPlayer).orElseThrow(() -> new PlayerException("Player not found"));

        toUpdate.setName(name);
        toUpdate.setSurname(surname);
        toUpdate.setPosition(position);
        toUpdate.setNum(num);

        if(image != null && !image.isEmpty()){
            String urlImage = cloudinaryService.uploadImage(image);
            toUpdate.setUrlImage(urlImage);
        }
        return Mapper.mapToPlayerDTO(playerRepository.save(toUpdate));

    }

    public void deletePlayer(Long idPlayer){
        if(idPlayer == null || idPlayer <= 0){
            throw new RuntimeException("id is invalid");
        }
        playerRepository.deleteById(idPlayer);
    }
}
