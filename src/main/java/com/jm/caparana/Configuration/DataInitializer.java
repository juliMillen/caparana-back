package com.jm.caparana.Configuration;

import com.jm.caparana.Entity.Permission;
import com.jm.caparana.Entity.Role;
import com.jm.caparana.Entity.UserSec;
import com.jm.caparana.Repository.IPermissionRepository;
import com.jm.caparana.Repository.IRoleRepository;
import com.jm.caparana.Repository.IUserSecRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final IPermissionRepository permissionRepository;

    private final IRoleRepository roleRepository;

    private final IUserSecRepository userSecRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Permission create = createPermissionIfNotExists("CREATE");
        Permission read = createPermissionIfNotExists("READ");
        Permission update = createPermissionIfNotExists("UPDATE");
        Permission delete = createPermissionIfNotExists("DELETE");

        createRoleIfNotExists("ADMIN", Set.of(create,read,update,delete));
        if(userSecRepository.findByUsername(adminUsername).isEmpty()){
            Role adminRole = roleRepository.findByRole("ADMIN").orElseThrow();
            UserSec admin = new UserSec();
            admin.setUsername(adminUsername);
            admin.setPassword(adminPassword);
            admin.setRolList(Set.of(adminRole));
            admin.setEnable(true);
            admin.setAccountNonExpired(true);
            admin.setAccountNonLocked(true);
            admin.setCredentialsNonExpired(true);
            userSecRepository.save(admin);
        }
    }

    private Permission createPermissionIfNotExists(String name){
        return permissionRepository.findByPermissionName(name).orElseGet(() ->{
            Permission p = new Permission();
            p.setPermissionName(name);
            return permissionRepository.save(p);
        });
    }

    private Role createRoleIfNotExists(String name, Set<Permission>permission){
        return roleRepository.findByRole(name).orElseGet(()-> {
            Role r = new Role();
            r.setRole(name);
            r.setPermissions(permission);
            return roleRepository.save(r);
        });
    }
}
