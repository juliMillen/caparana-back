package com.jm.caparana.Service;

import com.jm.caparana.DTO.MatchDTO;
import com.jm.caparana.Entity.Match;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.IMatchRepository;
import com.jm.caparana.Exception.MatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MatchService {

    @Autowired
    private IMatchRepository matchRepository;


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

    public MatchDTO save(MatchDTO matchDTO){
        Match toCreate = Match.builder()
                .rival(matchDTO.getRival())
                .dateTime(matchDTO.getDateTime())
                .location(matchDTO.getLocation())
                .teamGoals(matchDTO.getTeamGoals())
                .rivalGoals(matchDTO.getRivalGoals())
                .state(matchDTO.getState())
                .build();
        return Mapper.mapToMatchDTO(matchRepository.save(toCreate));
    }

    public MatchDTO updateMatch(Long idMatch, MatchDTO dto){
        Match toUpdate= matchRepository.findById(idMatch).orElseThrow(()->new MatchException("Match not found"));

        if(dto.getRival() != null){
            toUpdate.setRival(dto.getRival());
        }

        if(dto.getDateTime() != null){
            toUpdate.setDateTime(dto.getDateTime());
        }

        if(dto.getLocation() != null){
            toUpdate.setLocation(dto.getLocation());
        }

        if(dto.getTeamGoals() != null){
            toUpdate.setTeamGoals(dto.getTeamGoals());
        }

        if(dto.getRivalGoals() != null){
            toUpdate.setRivalGoals(dto.getRivalGoals());
        }

        if(dto.getState() != null){
            toUpdate.setState(dto.getState());
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
