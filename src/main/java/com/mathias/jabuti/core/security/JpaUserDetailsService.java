package com.mathias.jabuti.core.security;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mathias.jabuti.domain.model.User;
import com.mathias.jabuti.domain.repository.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new AuthUser(user, getAuthorities(user));
    }

    public Collection<GrantedAuthority> getAuthorities(User user) {
        return user.getGroups().stream()
            .flatMap(grupo -> grupo.getPermissions().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getName())))
            .collect(Collectors.toSet());
    }

}