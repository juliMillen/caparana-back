package com.jm.caparana.Controller;

import com.jm.caparana.Entity.Permission;
import com.jm.caparana.Entity.Role;
import com.jm.caparana.Service.PermissionService;
import com.jm.caparana.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Role>> getRoles(){
        List<Role> roleList = roleService.findAll();
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id){
        Role role = roleService.findById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        Set<Permission> permissionsList = new HashSet<>();
        Permission readPermission;

        //Recuperar permiso por ID
        for(Permission p: role.getPermissions()){
            readPermission = permissionService.findById(p.getId());
            if(readPermission != null){
                permissionsList.add(readPermission);
            }
        }
        role.setPermissions(permissionsList);
        Role newRole = roleService.save(role);
        return new ResponseEntity<>(newRole,HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> updateRole(@RequestBody Role role){
        Role toUpdate = roleService.findById(role.getId());
        roleService.update(toUpdate);
        return new ResponseEntity<>(toUpdate,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        roleService.deleteById(id);
        return new ResponseEntity<>("Role has been deleted", HttpStatus.NO_CONTENT);
    }
}
