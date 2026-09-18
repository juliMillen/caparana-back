package com.jm.caparana.Service;

import com.jm.caparana.DTO.ClubDTO;
import com.jm.caparana.Entity.Club;
import com.jm.caparana.Exception.ClubException;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.IClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ClubService {

    @Autowired
    private IClubRepository clubRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public ClubDTO findClubById(Long idClub){
        if(idClub == null || idClub <= 0){
            throw new RuntimeException("id invalid");
        }
        return Mapper.mapToClubDTO(clubRepository.findById(idClub).orElseThrow(() -> new ClubException("Club not found")));
    }

    public ClubDTO save(String name, LocalDate fundationDate, String history, String stadiumHistory,
                        String colorsHistory, List<String> titles, MultipartFile urlImageStadium, MultipartFile urlImageShield) throws IOException {
        String imageStadium = cloudinaryService.uploadImage(urlImageStadium);
        String imageShield = cloudinaryService.uploadImage(urlImageShield);

        Club nuevo = Club.builder()
                .name(name)
                .fundationDate(fundationDate)
                .history(history)
                .stadiumHistory(stadiumHistory)
                .colorsHistory(colorsHistory)
                .titles(titles)
                .urlImageShield(imageShield)
                .urlImageStadium(imageStadium)
                .build();
        return Mapper.mapToClubDTO(clubRepository.save(nuevo));
    }

    public ClubDTO updateClub(Long id,ClubDTO clubDTO){
        Club toUpdate = clubRepository.findById(id).orElseThrow(() -> new ClubException("club not found"));
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
