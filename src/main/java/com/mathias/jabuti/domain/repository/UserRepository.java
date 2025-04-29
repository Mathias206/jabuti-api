package com.mathias.jabuti.domain.repository;

import com.mathias.jabuti.domain.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); 
}
