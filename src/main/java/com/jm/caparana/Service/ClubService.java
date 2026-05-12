package com.jm.caparana.Service;

import com.jm.caparana.DTO.ClubDTO;
import com.jm.caparana.Entity.Club;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.IClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubService {

    @Autowired
    private IClubRepository clubRepository;

    public ClubDTO findClubById(Long idClub){
        if(idClub == null || idClub <= 0){
            throw new RuntimeException("id invalid");
        }
        return clubRepository.findById(idClub).orElseThrow(() -> new RuntimeException("club not found"));
    }

    public ClubDTO save(ClubDTO clubDTO){
        Club nuevo = Club.builder()
                .name(clubDTO.getName())
                .fundationDate(clubDTO.getFundationDate())
                .history(clubDTO.getHistory())
                .stadiumHistory(clubDTO.getStadiumHistory())
                .colorsHistory(clubDTO.getColorsHistory())
                .urlImageShield(clubDTO.getUrlImageStadium())
                .urlImageStadium(clubDTO.getUrlImageStadium())
                .build();
        return Mapper.mapToClubDTO(clubRepository.save(nuevo));
    }

    public ClubDTO updateClub(Long id,ClubDTO clubDTO){
        Club toUpdate = clubRepository.findById(id).orElseThrow(() -> new RuntimeException("club not found"));
        toUpdate.setName(clubDTO.getName());
        toUpdate.setFundationDate(clubDTO.getFundationDate());
        toUpdate.setHistory(clubDTO.getHistory());
        toUpdate.setStadiumHistory(clubDTO.getStadiumHistory());
        toUpdate.setColorsHistory(clubDTO.getColorsHistory());
        toUpdate.setUrlImageStadium(clubDTO.getUrlImageStadium());
        toUpdate.setUrlImageShield(clubDTO.getUrlImageShield());
        return Mapper.mapToClubDTO(clubRepository.save(toUpdate));

    }

    public void deleteClub(Long idClub){
        if(idClub == null || idClub <= 0){
            throw new RuntimeException("id invalid");
        }
        clubRepository.deleteById(idClub);
    }
}
