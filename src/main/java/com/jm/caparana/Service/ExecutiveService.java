package com.jm.caparana.Service;

import com.jm.caparana.Entity.Categority;
import com.jm.caparana.Entity.Executive;
import com.jm.caparana.Entity.Player;
import com.jm.caparana.Repository.IExecutiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExecutiveService {

    @Autowired
    private IExecutiveRepository executiveRepository;

    public List<Executive> findAllExecutives(){
        return executiveRepository.findAll();
    }

    public Executive findExecutiveById(Long idExecutive){
        if(idExecutive == null || idExecutive <= 0){
            throw new RuntimeException("id invalid");
        }
        return executiveRepository.findById(idExecutive).orElseThrow(() -> new RuntimeException("executive not found"));
    }

    public Executive save(Executive executive){
        return executiveRepository.save(executive);
    }

    public Executive updateExecutive(Long idExecutive, Executive executive){
        Executive toUpdate = executiveRepository.findById(idExecutive).orElseThrow(() -> new RuntimeException("executive not found"));
        toUpdate.setName(executive.getName());
        toUpdate.setSurname(executive.getSurname());
        toUpdate.setRole(executive.getRole());
        toUpdate.setUrlImage(executive.getUrlImage());
        executiveRepository.save(toUpdate);
        return toUpdate;
    }

    public void deleteExecutive(Long idExecutive){
        if(idExecutive == null || idExecutive <= 0){
            throw new RuntimeException("id is invalid");
        }
        executiveRepository.deleteById(idExecutive);
    }
}
