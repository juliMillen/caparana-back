package com.jm.caparana.Controller;

import com.jm.caparana.Entity.Role;
import com.jm.caparana.Entity.UserSec;
import com.jm.caparana.Service.RoleService;
import com.jm.caparana.Service.UserSecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserSecController {

    @Autowired
    private UserSecService userSecService;

    @Autowired
    private RoleService roleService;

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<UserSec>> getAllUsers(){
        List<UserSec> userList = userSecService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserSec> getUserById(@PathVariable Long id){
        UserSec user = userSecService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserSec> createUser(@RequestBody UserSec user){
        Set<Role> roles = new HashSet<>();
        Role readRole;

        //encripto contraseña
        user.setPassword(userSecService.encryptPassword(user.getPassword()));

        //recupero el permiso por ID
        for(Role rol: user.getRolList()){
            readRole = roleService.findById(rol.getId());
            if(readRole != null){
                roles.add(readRole);
            }
        }
        if(!roles.isEmpty()){
            user.setRolList(roles);
            UserSec newUser = userSecService.save(user);
            return new ResponseEntity<>(newUser,HttpStatus.CREATED);
        }
        return null;
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserSec> updateUser(@RequestBody UserSec user){
        UserSec toUpdate = userSecService.findById(user.getId());
        if(toUpdate != null){
            toUpdate.setUsername(user.getUsername());
            toUpdate.setPassword(user.getPassword());
            toUpdate.setRolList(user.getRolList());
            userSecService.save(toUpdate);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        userSecService.deleteById(id);
        return new ResponseEntity<>("User has been deleted", HttpStatus.NO_CONTENT);
    }
}
