package com.jm.caparana.Service;

import com.jm.caparana.Entity.Role;
import com.jm.caparana.Repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private IRoleRepository roleRepository;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role findById(Long id){
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public Role save(Role role){
        return roleRepository.save(role);
    }

    public Role update(Role role){
        if(roleRepository.findById(role.getId()).isPresent()){
            Role toUpdate= new Role();
            toUpdate.setId(role.getId());
            toUpdate.setRole(role.getRole());
            toUpdate.setPermissions(role.getPermissions());
            return roleRepository.save(toUpdate);
        }
        return null;
    }

    public void deleteById(Long id){
        roleRepository.deleteById(id);
    }
}
