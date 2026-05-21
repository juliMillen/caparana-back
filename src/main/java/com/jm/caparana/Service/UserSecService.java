package com.jm.caparana.Service;

import com.jm.caparana.Entity.UserSec;
import com.jm.caparana.Repository.IUserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSecService {

    @Autowired
    private IUserSecRepository userSecRepository;

    public List<UserSec> findAll(){
        return userSecRepository.findAll();
    }

    public UserSec findById(Long id){
        return userSecRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
    }

    public UserSec save(UserSec user){
        return userSecRepository.save(user);
    }

    public UserSec update(UserSec user){
        if(userSecRepository.findById(user.getId()).isPresent()){
            UserSec toUpdate = new UserSec();
            toUpdate.setId(user.getId());
            toUpdate.setUsername(user.getUsername());
            toUpdate.setEnable(user.isEnable());
            toUpdate.setAccountNonLocked(user.isAccountNonLocked());
            toUpdate.setAccountNotExpired(user.isAccountNotExpired());
            toUpdate.setRolList(user.getRolList());
            userSecRepository.save(toUpdate);
        }
        return null;
    }

    public void deleteById(Long id){
        userSecRepository.deleteById(id);
    }

    public String encryptPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
