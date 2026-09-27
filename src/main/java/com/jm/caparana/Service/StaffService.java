package com.jm.caparana.Service;


import com.jm.caparana.DTO.StaffDTO;
import com.jm.caparana.Entity.Categority;
import com.jm.caparana.Entity.Staff;
import com.jm.caparana.Enum.StaffRole;
import com.jm.caparana.Exception.CategorityException;
import com.jm.caparana.Exception.StaffException;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.ICategorityRepository;
import com.jm.caparana.Repository.IStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StaffService {

    @Autowired
    private IStaffRepository staffRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    private ICategorityRepository categorityRepository;

    public List<StaffDTO> findAllStaff(){
        return staffRepository.findAll().stream()
                .map(Mapper::mapToStaffDTO)
                .toList();
    }

    public StaffDTO findStaffById(Long idStaff){
        if(idStaff == null || idStaff <= 0){
            throw new RuntimeException("id invalid");
        }
        return Mapper.mapToStaffDTO(staffRepository.findById(idStaff).orElseThrow(() -> new StaffException("Staff not found")));
    }

    public StaffDTO save(Long idCategority, String name, String surname, String position, StaffRole role, MultipartFile image)throws IOException{
        String urlImage= cloudinaryService.uploadImage(image);
        Categority categority = categorityRepository.findById(idCategority).orElseThrow(() -> new CategorityException("Categority not found"));

        Staff toCreate = Staff.builder()
                .name(name)
                .surname(surname)
                .position(position)
                .role(role)
                .urlImage(urlImage)
                .categority(categority)
                .build();
        return Mapper.mapToStaffDTO(staffRepository.save(toCreate));
    }

    public StaffDTO updateStaff(Long idStaff, String name, String surname, String position, StaffRole role, MultipartFile image)throws IOException{
        Staff toUpdate = staffRepository.findById(idStaff).orElseThrow(() -> new StaffException("Staff not found"));
        toUpdate.setName(name);
        toUpdate.setSurname(surname);
        toUpdate.setPosition(position);
        toUpdate.setRole(role);

        if(image != null && !image.isEmpty()){
            String urlImage = cloudinaryService.uploadImage(image);
            toUpdate.setUrlImage(urlImage);
        }
        return Mapper.mapToStaffDTO(toUpdate);
    }

    public void deleteStaff(Long idStaff){
        if(idStaff == null || idStaff <= 0){
            throw new RuntimeException("id is invalid");
        }
        staffRepository.deleteById(idStaff);
    }
}
