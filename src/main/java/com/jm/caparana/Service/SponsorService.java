package com.jm.caparana.Service;

import com.jm.caparana.DTO.SponsorDTO;
import com.jm.caparana.Entity.Sponsor;
import com.jm.caparana.Exception.SponsorException;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.ISponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorService {

    @Autowired
    private ISponsorRepository sponsorRepository;

    public List<SponsorDTO> finAllSponsors(){
        return sponsorRepository.findAll().stream()
                .map(Mapper::mapToSponsorDTO)
                .toList();
    }

    public SponsorDTO findSponsorByID(Long id){
        if(id == null || id <= 0){
            throw new RuntimeException("Id invalid");
        }
        return Mapper.mapToSponsorDTO(sponsorRepository.findById(id).orElseThrow(()-> new SponsorException("Sponsor not found")));
    }

    public SponsorDTO createSponsor(SponsorDTO sponsorDTO){
        Sponsor toCreate = Sponsor.builder()
                .name(sponsorDTO.getName())
                .urlImage(sponsorDTO.getUrlImage())
                .build();
        return Mapper.mapToSponsorDTO(sponsorRepository.save(toCreate));
    }

    public void deleteSponsor(Long idSponsor){
        if(idSponsor == null || idSponsor <= 0){
            throw new RuntimeException("Id invalid");
        }
        sponsorRepository.deleteById(idSponsor);
    }
}
