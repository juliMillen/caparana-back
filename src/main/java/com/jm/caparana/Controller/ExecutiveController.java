package com.jm.caparana.Controller;

import com.jm.caparana.Entity.Executive;
import com.jm.caparana.Service.ExecutiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/executive")
public class ExecutiveController {

    @Autowired
    private ExecutiveService executiveService;

    @GetMapping("/")
    public ResponseEntity<List<Executive>> getAllExecutives(){
        List<Executive> executiveList = executiveService.findAllExecutives();
        return new ResponseEntity<>(executiveList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Executive> getExecutiveById(@PathVariable Long id){
        Executive toSearch = executiveService.findExecutiveById(id);
        return new ResponseEntity<>(toSearch, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Executive> saveExecutive(@RequestBody Executive executive){
        return new ResponseEntity<>(executiveService.save(executive),HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Executive> updateExecutive(@PathVariable Long id, @RequestBody Executive executive){
        return new ResponseEntity<>(executiveService.updateExecutive(id,executive), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExecutive(@PathVariable Long id){
        executiveService.deleteExecutive(id);
        return new ResponseEntity<>("Executive Deleted Succesfully", HttpStatus.NOT_FOUND);
    }
}
