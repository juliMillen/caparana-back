package com.jm.caparana.Service;

import com.jm.caparana.DTO.DisciplineDTO;
import com.jm.caparana.Entity.Discipline;
import com.jm.caparana.Exception.DisciplineException;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.IDisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineService {

    @Autowired
    private IDisciplineRepository disciplineRepository;

    public List<DisciplineDTO> findAllDisciplines(){
        return disciplineRepository.findAll().stream()
                .map(Mapper::mapToDisciplineDTO)
                .toList();
    }

    public DisciplineDTO findDisciplineById(Long idDiscipline){
        if(idDiscipline == null || idDiscipline <= 0){
            throw new RuntimeException("id invalid");
        }
        return Mapper.mapToDisciplineDTO(disciplineRepository.findById(idDiscipline).orElseThrow(() -> new DisciplineException("Discipline not found")));
    }

    public DisciplineDTO save(DisciplineDTO disciplineDTO){
        Discipline toCreate = Discipline.builder()
                .nameDiscipline(disciplineDTO.getNameDiscipline())
                .description(disciplineDTO.getDescription())
                .ubication(disciplineDTO.getUbication())
                .professorAsig(disciplineDTO.getProfessorAsig())
                .schedule(disciplineDTO.getSchedule())
                .build();
        return Mapper.mapToDisciplineDTO(disciplineRepository.save(toCreate));
    }

    public DisciplineDTO updateDiscipline(Long idDiscipline, DisciplineDTO disciplineDTO){
        Discipline toUpdate = disciplineRepository.findById(idDiscipline).orElseThrow(()-> new RuntimeException("Discipline not found"));
        toUpdate.setNameDiscipline(disciplineDTO.getNameDiscipline());
        toUpdate.setDescription(disciplineDTO.getDescription());
        toUpdate.setProfessorAsig(disciplineDTO.getProfessorAsig());
        toUpdate.setUbication(disciplineDTO.getUbication());
        toUpdate.setSchedule(disciplineDTO.getSchedule());
        return Mapper.mapToDisciplineDTO(disciplineRepository.save(toUpdate));
    }

    public void deleteDiscipline(Long idDiscipline){
        if(idDiscipline == null || idDiscipline <= 0){
            throw new RuntimeException("id invalid");
        }

        disciplineRepository.deleteById(idDiscipline);
    }
}
