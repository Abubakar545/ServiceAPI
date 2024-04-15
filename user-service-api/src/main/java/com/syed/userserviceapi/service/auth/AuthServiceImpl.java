package com.syed.userserviceapi.service.auth;

import com.syed.userserviceapi.entity.AppUser;
import com.syed.userserviceapi.repository.AppUserRepository;
import com.syed.userserviceapi.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    public final AuthenticationManager authenticatonManager;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepo;

    @Override
    public String login(String username, String password) {
       var authToken = new UsernamePasswordAuthenticationToken(username, password);

       var authenticate = authenticatonManager.authenticate(authToken);

       // Generate Token
       return JwtUtils.generateToken( ((UserDetails)(authenticate.getPrincipal())).getUsername());
    }

    @Override
    public String signup(String name, String username, String password) {
        // Check whether user already exists or not
        if(appUserRepo.existsByUsername(username)){
            throw new RuntimeException("user already exists");
        }

        // Encode password
        var encodedPassword = passwordEncoder.encode(password);

        // Authorities
        var authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // Create App User
        var appUser = AppUser.builder()
                .name(name)
                .username(username)
                .password(encodedPassword)
                .authorities(authorities)
                .build();


        // Save user
        appUserRepo.save(appUser);

        // Generate Token
        return JwtUtils.generateToken(username);

    }
}
