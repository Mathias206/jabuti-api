package com.mathias.jabuti.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JabutiSecurityManager {

    public Long getUserById() {
        var authUser = (AuthUser) getAuthentication().getPrincipal();
        return authUser.getUserId();
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
