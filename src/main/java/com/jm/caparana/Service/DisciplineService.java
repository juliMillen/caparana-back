package com.jm.caparana.Service;

import com.jm.caparana.Entity.Discipline;
import com.jm.caparana.Repository.IDisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineService {

    @Autowired
    private IDisciplineRepository disciplineRepository;

    public List<Discipline> findAllDisciplines(){
        return disciplineRepository.findAll();
    }

    public Discipline findDisciplineById(Long idDiscipline){
        if(idDiscipline == null || idDiscipline <= 0){
            throw new RuntimeException("id invalid");
        }
        return disciplineRepository.findById(idDiscipline).orElseThrow(() -> new RuntimeException("discipline not found"));
    }

    public Discipline save(Discipline discipline){
        return disciplineRepository.save(discipline);
    }

    public Discipline updateDiscipline(Long idDiscipline, Discipline discipline){
        Discipline toUpdate = disciplineRepository.findById(idDiscipline).orElseThrow(()-> new RuntimeException("discipline not found"));
        toUpdate.setNameDiscipline(discipline.getNameDiscipline());
        toUpdate.setDescription(discipline.getDescription());
        toUpdate.setSchedule(discipline.getSchedule());
        toUpdate.setProfessorAsig(discipline.getProfessorAsig());
        toUpdate.setUbication(discipline.getUbication());
        disciplineRepository.save(toUpdate);
        return toUpdate;
    }

    public void deleteDiscipline(Long idDiscipline){
        if(idDiscipline == null || idDiscipline <= 0){
            throw new RuntimeException("id invalid");
        }

        disciplineRepository.deleteById(idDiscipline);
    }
}
