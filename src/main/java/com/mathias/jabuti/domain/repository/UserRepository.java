package com.mathias.jabuti.domain.repository;

import com.mathias.jabuti.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
