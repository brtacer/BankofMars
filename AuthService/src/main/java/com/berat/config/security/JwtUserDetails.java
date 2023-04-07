package com.berat.config.security;

import com.berat.model.Auth;
import com.berat.model.Status;
import com.berat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetails implements UserDetailsService {
    @Autowired
    private AuthService authService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public UserDetails loadUserById(Long id){
        Optional<Auth> auth = authService.findById(id);
        if (auth.isEmpty())
            return null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(auth.get().getRole().name()));
        boolean status = auth.get().getStatus().equals(Status.APPROVED) ? false : true;
        return User.builder()
                .username(auth.get().getUsername())
                .password("")
                .accountExpired(false)
                .accountLocked(status)
                .authorities(authorities)
                .build();
    }
}
