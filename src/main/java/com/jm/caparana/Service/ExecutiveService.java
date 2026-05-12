package com.jm.caparana.Service;

import com.jm.caparana.DTO.ExecutiveDTO;
import com.jm.caparana.Entity.Categority;
import com.jm.caparana.Entity.Executive;
import com.jm.caparana.Entity.Player;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.IExecutiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecutiveService {

    @Autowired
    private IExecutiveRepository executiveRepository;

    public List<ExecutiveDTO> findAllExecutives(){
        return executiveRepository.findAll().stream()
                .map(Mapper::mapToExecutiveDTO)
                .toList();
    }

    public ExecutiveDTO findExecutiveById(Long idExecutive){
        if(idExecutive == null || idExecutive <= 0){
            throw new RuntimeException("id invalid");
        }
        return Mapper.mapToExecutiveDTO(executiveRepository.findById(idExecutive).orElseThrow(() -> new RuntimeException("executive not found")));
    }

    public ExecutiveDTO save(ExecutiveDTO executiveDTO){
        Executive toCreate = Executive.builder()
                .name(executiveDTO.getName())
                .surname(executiveDTO.getSurname())
                .position(executiveDTO.getPosition())
                .urlImage(executiveDTO.getUrlImage())
                .build();
        return Mapper.mapToExecutiveDTO(executiveRepository.save(toCreate));
    }

    public ExecutiveDTO updateExecutive(Long idExecutive, ExecutiveDTO executiveDTO){
        Executive toUpdate = executiveRepository.findById(idExecutive).orElseThrow(() -> new RuntimeException("Executive not found"));
        toUpdate.setName(executiveDTO.getName());
        toUpdate.setSurname(executiveDTO.getSurname());
        toUpdate.setPosition(executiveDTO.getPosition());
        toUpdate.setUrlImage(executiveDTO.getUrlImage());
        return Mapper.mapToExecutiveDTO(executiveRepository.save(toUpdate));
    }

    public void deleteExecutive(Long idExecutive){
        if(idExecutive == null || idExecutive <= 0){
            throw new RuntimeException("id is invalid");
        }
        executiveRepository.deleteById(idExecutive);
    }
}
