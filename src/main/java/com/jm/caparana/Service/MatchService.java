package com.jm.caparana.Service;

import com.jm.caparana.DTO.MatchDTO;
import com.jm.caparana.Entity.Match;
import com.jm.caparana.Enum.MatchState;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.IMatchRepository;
import com.jm.caparana.Exception.MatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class MatchService {

    @Autowired
    private IMatchRepository matchRepository;

    @Autowired
    private CloudinaryService cloudinaryService;


    public List<MatchDTO> findAllMatches(){
        return matchRepository.findAll().stream()
                .map(Mapper::mapToMatchDTO)
                .toList();
    }

    public MatchDTO findMatchById(Long idMatch){
        if(idMatch == null || idMatch <= 0){
            throw new MatchException("id invalid");
        }
        return Mapper.mapToMatchDTO(matchRepository.findById(idMatch).orElseThrow(() -> new MatchException("Match not found")));
    }

    public MatchDTO save(String rival, LocalDateTime dateTime, String location, int teamGoals, int rivalGoals, MatchState state, MultipartFile image)throws IOException {
        String urlImage= cloudinaryService.uploadImage(image);

        Match toCreate = Match.builder()
                .rival(rival)
                .dateTime(dateTime)
                .location(location)
                .teamGoals(teamGoals)
                .rivalGoals(rivalGoals)
                .state(state)
                .urlShieldRival(urlImage)
                .build();
        return Mapper.mapToMatchDTO(matchRepository.save(toCreate));
    }

    public MatchDTO updateMatch(Long idMatch, String rival, LocalDateTime dateTime, String location, Integer teamGoals, Integer rivalGoals, MatchState state, MultipartFile image)throws IOException{
        Match toUpdate= matchRepository.findById(idMatch).orElseThrow(()->new MatchException("Match not found"));

        if(rival != null){
            toUpdate.setRival(rival);
        }

        if(dateTime != null){
            toUpdate.setDateTime(dateTime);
        }

        if(location != null){
            toUpdate.setLocation(location);
        }

        if(teamGoals != null){
            toUpdate.setTeamGoals(teamGoals);
        }

        if(rivalGoals != null){
            toUpdate.setRivalGoals(rivalGoals);
        }

        if(state != null){
            toUpdate.setState(state);
        }

        if(image != null){
            String urlImage = cloudinaryService.uploadImage(image);
            toUpdate.setUrlShieldRival(urlImage);
        }
        return Mapper.mapToMatchDTO(matchRepository.save(toUpdate));
    }

    public void deleteMatch(Long idMatch){
        if(idMatch == null || idMatch <= 0){
            throw new MatchException("id invalid");
        }
        Match match = matchRepository.findById(idMatch).orElseThrow(()-> new MatchException("Match not found"));
        matchRepository.delete(match);
    }
}
