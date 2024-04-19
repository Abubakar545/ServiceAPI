package com.syed.securityservice.service.auth;

import com.syed.securityservice.entity.AppUser;
import com.syed.securityservice.exception.EmailAlreadyExistsException;
import com.syed.securityservice.exception.UsernameAlreadyExistsException;
import com.syed.securityservice.repository.AppUserRepository;
import com.syed.securityservice.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
    public String signup(String name, String username, String email, String password ) {
        // Check whether user already exists by username or email
        if (appUserRepo.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException("User already exists with username: " + username);
        }
        if (appUserRepo.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("User already exists with email: " + email);
        }

        // Encode password
        String encodedPassword = passwordEncoder.encode(password);

        // Authorities as string (example: "ROLE_USER")
        String authorities = "ROLE_USER"; // You can customize this as needed

        // Create App User
        AppUser appUser = AppUser.builder()
                .name(name)
                .username(username)
                .password(encodedPassword)
                .email(email)
                .authorities(authorities) // Assign authorities as string
                .build();

        // Save user
        appUserRepo.save(appUser);

        // Generate Token
        return JwtUtils.generateToken(username);
    }


}
