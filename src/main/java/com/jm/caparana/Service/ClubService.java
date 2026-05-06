package com.jm.caparana.Service;

import com.jm.caparana.Entity.Club;
import com.jm.caparana.Repository.IClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubService {

    @Autowired
    private IClubRepository clubRepository;

    public Club findClubById(Long idClub){
        if(idClub == null || idClub <= 0){
            throw new RuntimeException("id invalid");
        }
        return clubRepository.findById(idClub).orElseThrow(() -> new RuntimeException("club not found"));
    }

    public Club save(Club club){
        return clubRepository.save(club);
    }

    public Club updateClub(Club club){
        Club toUpdate = clubRepository.findById(1L).orElseThrow(() -> new RuntimeException("club not found"));
        toUpdate.setName(club.getName());
        toUpdate.setHistory(club.getHistory());
        toUpdate.setTitulos(club.getTitulos());
        toUpdate.setColorsHistory(club.getColorsHistory());
        toUpdate.setFundationDate(club.getFundationDate());
        toUpdate.setStadiumHistory(club.getStadiumHistory());
        toUpdate.setUrlImageShield(club.getUrlImageShield());
        toUpdate.setUrlImageStadium(club.getUrlImageStadium());
        clubRepository.save(toUpdate);
        return clubRepository.save(toUpdate);
    }

    public void deleteClub(Long idClub){
        if(idClub == null || idClub <= 0){
            throw new RuntimeException("id invalid");
        }
        clubRepository.deleteById(idClub);
    }
}
