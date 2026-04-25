package com.jm.caparana.Service;

import com.jm.caparana.Entity.Categority;
import com.jm.caparana.Entity.Player;
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

    private List<Player> findAllPlayers(){
        return playerRepository.findAll();
    }

    private Player findPlayerById(Long idPlayer){
        if(idPlayer == null || idPlayer <= 0){
            throw new RuntimeException("id invalid");
        }
        return playerRepository.findById(idPlayer).orElseThrow(() -> new RuntimeException("player not found"));
    }

    public Player save(Long idCategority,Player player){
        Categority categority = categorityRepository.findById(idCategority).orElseThrow(() -> new RuntimeException("categority not found"));
        player.setCategority(categority);
        return playerRepository.save(player);
    }

    public Player updatePlayer(Long idPlayer, Player player){
        Player toUpdate = playerRepository.findById(idPlayer).orElseThrow(() -> new RuntimeException("player not found"));
        toUpdate.setName(player.getName());
        toUpdate.setSurname(player.getSurname());
        toUpdate.setCategority(player.getCategority());
        toUpdate.setPosition(player.getPosition());
        toUpdate.setNum(player.getNum());
        toUpdate.setUrlImage(player.getUrlImage());
        playerRepository.save(toUpdate);
        return toUpdate;
    }

    public void deletePlayer(Long idPlayer){
        if(idPlayer == null || idPlayer <= 0){
            throw new RuntimeException("id is invalid");
        }
        playerRepository.deleteById(idPlayer);
    }
}
