package com.jm.caparana.Service;

import com.jm.caparana.Entity.Permission;
import com.jm.caparana.Repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    public List<Permission> findAll(){
        return permissionRepository.findAll();
    }

    public Permission findById(Long id){
        return permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission not found"));
    }

    public Permission save(Permission permission){
        return permissionRepository.save(permission);
    }

    public void update(Permission permission){
        permissionRepository.save(permission);
    }

    public void deleteById(Long id){
        permissionRepository.deleteById(id);
    }
}
