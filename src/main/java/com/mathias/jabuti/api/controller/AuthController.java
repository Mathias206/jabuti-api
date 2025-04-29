package com.mathias.jabuti.api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mathias.jabuti.api.model.LoginRequestDTO;
import com.mathias.jabuti.core.security.AuthUser;
import com.mathias.jabuti.core.security.JwtUtil;


@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	
	private final JwtUtil jwtUtil;
	
	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	// TODO: mudar para /token
	@PostMapping("/login")
	public ResponseEntity<?> entrar(@RequestBody LoginRequestDTO request) {
		var credentials = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
		var auth = authenticationManager.authenticate(credentials);
		var token = jwtUtil.generateToken((AuthUser) auth.getPrincipal());
		return ResponseEntity.ok(Map.of("access_token", token));
	}
	
}