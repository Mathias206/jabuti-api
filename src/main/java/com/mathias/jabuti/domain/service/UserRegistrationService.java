package com.mathias.jabuti.domain.service;

import com.mathias.jabuti.domain.exception.EntityNotFoundException;
import com.mathias.jabuti.domain.model.User;
import com.mathias.jabuti.domain.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

	@Autowired
	private UserRepository userRepository;

	public User findOrFail(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(User.class, userId));
	} 
}
