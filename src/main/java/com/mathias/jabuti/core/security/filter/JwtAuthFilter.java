package com.mathias.jabuti.core.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mathias.jabuti.api.model.UserEmailPasswordProjection;
import com.mathias.jabuti.core.security.AuthUser;
import com.mathias.jabuti.core.security.JpaUserDetailsService;
import com.mathias.jabuti.core.security.JwtUtil;
import com.mathias.jabuti.domain.model.User;
import com.mathias.jabuti.domain.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JpaUserDetailsService jpaUserDetailsService;

    private final JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    private UserRepository userRepository;


    public JwtAuthFilter(JpaUserDetailsService jpaUserDetailsService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            try {

                var username = extractUsername(jwt);
                var authorities = toGrantedAuthority(jwtUtil.getAuthorities(jwt));

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    var user = userRepository.findByEmailProjected(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                    var userDetails = new AuthUser(user.getId(), user.getEmail(), user.getPassword(), authorities);
                    
                    if (isTokenValid(jwt, userDetails)) {
                        var authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            Collections.emptyList());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

        }

        filterChain.doFilter(request, response);

        return;

    }

	public String extractUsername(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(jwtUtil.getJwtSecret().getBytes())
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

    public boolean isTokenValid(String token, AuthUser userDetails) {
		String username = extractUsername(token);
		return username.equals(userDetails.getUsername());
	}

    public Collection<GrantedAuthority> toGrantedAuthority(List<String> authorities) {
        return authorities.stream()
            .map(authoritie -> new SimpleGrantedAuthority(authoritie))
            .collect(Collectors.toSet());
    }

}
