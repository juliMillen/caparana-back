package com.jm.caparana.Controller;

import com.jm.caparana.Entity.Discipline;
import com.jm.caparana.Service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discipline")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping("/")
    public ResponseEntity<List<Discipline>> getDisciplines(){
        List<Discipline> disciplineList = disciplineService.findAllDisciplines();
        return new ResponseEntity<>(disciplineList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id){
        return new ResponseEntity<>(disciplineService.findDisciplineById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Discipline> createDiscipline(@RequestBody Discipline discipline){
        return new ResponseEntity<>(disciplineService.save(discipline),HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable Long id, @RequestBody Discipline discipline){
        return new ResponseEntity<>(disciplineService.updateDiscipline(id,discipline),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDiscipline(@PathVariable Long id){
        disciplineService.deleteDiscipline(id);
        return new ResponseEntity<>("Discipline deleted succesfully", HttpStatus.NOT_FOUND);
    }
}
