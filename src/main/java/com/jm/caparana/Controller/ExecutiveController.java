package com.jm.caparana.Controller;

import com.jm.caparana.DTO.ExecutiveDTO;
import com.jm.caparana.Entity.Executive;
import com.jm.caparana.Service.ExecutiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/executive")
public class ExecutiveController {

    @Autowired
    private ExecutiveService executiveService;

    @GetMapping("/")
    public ResponseEntity<List<ExecutiveDTO>> getAllExecutives(){
        List<ExecutiveDTO> executiveList = executiveService.findAllExecutives();
        return new ResponseEntity<>(executiveList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExecutiveDTO> getExecutiveById(@PathVariable Long id){
        ExecutiveDTO toSearch = executiveService.findExecutiveById(id);
        return new ResponseEntity<>(toSearch, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<ExecutiveDTO> saveExecutive(@RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("position") String position,
                                                      @RequestParam("image")MultipartFile image) throws IOException {
        return new ResponseEntity<>(executiveService.save(name,surname,position,image),HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ExecutiveDTO> updateExecutive(@PathVariable Long id, @RequestBody ExecutiveDTO executive){
        return new ResponseEntity<>(executiveService.updateExecutive(id,executive), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<String> deleteExecutive(@PathVariable Long id){
        executiveService.deleteExecutive(id);
        return new ResponseEntity<>("Executive Deleted Succesfully", HttpStatus.NOT_FOUND);
    }
}
