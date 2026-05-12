package com.jm.caparana.Controller;

import com.jm.caparana.DTO.CategorityDTO;
import com.jm.caparana.Entity.Categority;
import com.jm.caparana.Service.CategorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categority")
public class CategorityController {

    @Autowired
    private CategorityService categorityService;

    @GetMapping("")
    public ResponseEntity<List<CategorityDTO>> getCategorities(){
        List<CategorityDTO> categorityList = categorityService.findAllCategorities();
        return new ResponseEntity<>(categorityList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorityDTO> getCategority(@PathVariable Long id){
        return new ResponseEntity<>(categorityService.findCategorityById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CategorityDTO> createCategority(@RequestBody CategorityDTO categority){
        return new ResponseEntity<>(categorityService.save(categority),HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<CategorityDTO> updateCategority(@PathVariable Long id, @RequestBody CategorityDTO categority){
        return new ResponseEntity<>(categorityService.updateCategority(id,categority),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategority(@PathVariable Long id){
      categorityService.deleteCategority(id);
      return new ResponseEntity<>("Categority deleted succesfully", HttpStatus.OK);
    }
}
