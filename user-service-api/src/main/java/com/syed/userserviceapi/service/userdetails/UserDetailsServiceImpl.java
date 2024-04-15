package com.syed.userserviceapi.service.userdetails;

import com.syed.userserviceapi.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var appUser = appUserRepo.findByUsername(username)
               .orElseThrow(()-> new UsernameNotFoundException("Username not found"));

       return new User(appUser.getUsername(), appUser.getPassword(), appUser.getAuthorities());
    }
}
