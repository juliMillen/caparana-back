package com.jm.caparana.Controller;

import com.jm.caparana.DTO.StaffDTO;
import com.jm.caparana.Enum.StaffRole;
import com.jm.caparana.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("")
    public ResponseEntity<List<StaffDTO>> getAllStaff(){
        List<StaffDTO> listStaff = staffService.findAllStaff();
        return new ResponseEntity<>(listStaff, HttpStatus.OK);
    }

    @GetMapping("/{idStaff}")
    public ResponseEntity<StaffDTO> getStaffById(@PathVariable Long idStaff){
        return new ResponseEntity<>(staffService.findStaffById(idStaff),HttpStatus.OK);
    }

    @PostMapping("/create/{idCategority}")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<StaffDTO> createStaff(@PathVariable Long idCategority, @RequestParam("name")String name,
                                                @RequestParam("surname")String surname, @RequestParam("position")String position,
                                                @RequestParam("role")StaffRole role, @RequestParam(value = "image",required = false)MultipartFile image)throws IOException {
       return new ResponseEntity<>(staffService.save(idCategority,name,surname,position,role,image),HttpStatus.CREATED);
    }

    @PatchMapping("/update/{idStaff}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<StaffDTO>updateStaff(@PathVariable Long idStaff, @RequestParam("name")String name, @RequestParam("surname")String surname,
                                               @RequestParam("position")String position, @RequestParam("role")StaffRole role, @RequestParam(value = "image",required = false)MultipartFile image)throws IOException{
        return new ResponseEntity<>(staffService.updateStaff(idStaff,name,surname,position,role,image),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idStaff}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<String> deleteStaff(@PathVariable Long idStaff){
        staffService.deleteStaff(idStaff);
        return new ResponseEntity<>("Staff deleted succesfully", HttpStatus.NO_CONTENT);
    }


}
