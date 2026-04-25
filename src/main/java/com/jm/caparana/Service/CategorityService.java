package com.jm.caparana.Service;

import com.jm.caparana.Entity.Categority;
import com.jm.caparana.Repository.ICategorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorityService {

    @Autowired
    private ICategorityRepository categorityRepository;

    public List<Categority> findAllCategorities(){
        return categorityRepository.findAll();
    }

    public Categority findCategorityById(Long idCategority){
        if(idCategority == null || idCategority <= 0){
            throw new RuntimeException("id invalid");
        }
        return categorityRepository.findById(idCategority).orElseThrow(() -> new RuntimeException("categority not found"));
    }

    public Categority save(Categority categority){
        return categorityRepository.save(categority);
    }

    public Categority updateCategority(Long idCategority, Categority categority){
        Categority toUpdate = categorityRepository.findById(idCategority).orElseThrow(() -> new RuntimeException("categority not found"));
        toUpdate.setNameCategority(categority.getNameCategority());
        toUpdate.setPlayerList(categority.getPlayerList());
        categorityRepository.save(toUpdate);
        return toUpdate;
    }

    public void deleteCategority(Long idCategority){
        if(idCategority == null || idCategority <= 0){
            throw new RuntimeException("id invalid");
        }
        categorityRepository.deleteById(idCategority);
    }
}
