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
import java.util.ArrayList;
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
                        String colorsHistory, List<String> titles, MultipartFile imageStadium, MultipartFile imageShield) throws IOException {
        if(clubRepository.count() > 0){
            throw new ClubException("A club already exists");
        }

        String urlImageStadium = cloudinaryService.uploadImage(imageStadium);
        String urlImageShield = cloudinaryService.uploadImage(imageShield);

        Club nuevo = Club.builder()
                .name(name)
                .fundationDate(fundationDate)
                .history(history)
                .stadiumHistory(stadiumHistory)
                .colorsHistory(colorsHistory)
                .titles(titles != null ? titles : new ArrayList<>())
                .urlImageShield(urlImageShield)
                .urlImageStadium(urlImageStadium)
                .build();
        return Mapper.mapToClubDTO(clubRepository.save(nuevo));
    }

    public ClubDTO updateClub(Long id, String name, LocalDate fundationDate, String history, String stadiumHistory,
                              String colorsHistory, List<String> titles, MultipartFile imageStadium, MultipartFile imageShield)throws  IOException{

        Club toUpdate = clubRepository.findById(id).orElseThrow(() -> new ClubException("club not found"));
        toUpdate.setName(name);
        toUpdate.setFundationDate(fundationDate);
        toUpdate.setHistory(history);
        toUpdate.setStadiumHistory(stadiumHistory);
        toUpdate.setColorsHistory(colorsHistory);

        if(titles != null){
            toUpdate.setTitles(titles);
        }

        if(imageStadium != null && !imageStadium.isEmpty()){
            toUpdate.setUrlImageStadium(cloudinaryService.uploadImage(imageStadium));
        }

        if(imageShield != null && !imageShield.isEmpty()){
            toUpdate.setUrlImageShield(cloudinaryService.uploadImage(imageShield));
        }
        return Mapper.mapToClubDTO(clubRepository.save(toUpdate));

    }

    public void deleteClub(Long idClub){
        if(idClub == null || idClub <= 0){
            throw new RuntimeException("id invalid");
        }
        clubRepository.deleteById(idClub);
    }
}
