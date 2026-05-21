package com.jm.caparana.Service;

import com.jm.caparana.DTO.AuthLoginRequestDTO;
import com.jm.caparana.DTO.AuthResponseDTO;
import com.jm.caparana.Entity.UserSec;
import com.jm.caparana.Repository.IUserSecRepository;
import com.jm.caparana.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUserSecRepository userSecRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSec newUser = userSecRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("The user: "+username+ " was not found"));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        newUser.getRolList().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermissionName())));

        return new User(newUser.getUsername(),
                newUser.getPassword(),
                newUser.isEnable(),
                newUser.isAccountNotExpired(),
                newUser.isAccountNonLocked(),
                newUser.isCredentialsNonExpired(),
                grantedAuthorities);
    }

    public AuthResponseDTO loginUser(AuthLoginRequestDTO authLoginRequest){
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accesToken = jwtUtils.createToken(authentication);
        AuthResponseDTO authResponse = new AuthResponseDTO(username,"Login OK", accesToken, true);
        return authResponse;
    }

    public Authentication authenticate(String username, String password){
        UserDetails userDetails = this.loadUserByUsername(username);
        if(userDetails == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,password, userDetails.getAuthorities());
    }
}
