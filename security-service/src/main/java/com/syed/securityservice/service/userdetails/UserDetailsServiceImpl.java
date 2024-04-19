package com.syed.securityservice.service.userdetails;

import com.syed.securityservice.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var appUser = appUserRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(appUser.getAuthorities().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }
}