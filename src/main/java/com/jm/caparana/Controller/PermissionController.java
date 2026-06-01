package com.jm.caparana.Controller;

import com.jm.caparana.Entity.Permission;
import com.jm.caparana.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Permission>> getPermissions(){
        List<Permission> listPermission = permissionService.findAll();
        return new ResponseEntity<>(listPermission, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id){
        Permission permission = permissionService.findById(id);
        return new ResponseEntity<>(permission,HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN)")
    public ResponseEntity<Permission> savePermission(@RequestBody Permission permission){
        Permission newPermission = permissionService.save(permission);
        return new ResponseEntity<>(newPermission,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN)")
    public ResponseEntity<Permission> updatePermission(@RequestBody Permission permission){
        permissionService.update(permission);
        return new ResponseEntity<>(permission,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN)")
    public ResponseEntity<String> deletePermission(@PathVariable Long id){
        permissionService.deleteById(id);
        return new ResponseEntity<>("Permission deleted succesfully", HttpStatus.NO_CONTENT);
    }
}
