package com.mathias.jabuti.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mathias.jabuti.domain.model.User;
import com.mathias.jabuti.domain.repository.UserRepository;
import com.mathias.jabuti.domain.service.UserRegistrationService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserRegistrationService userService;

    @GetMapping
    private List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping("/{userId}")
    private User retrieve(@PathVariable("userId") Long userId) {
        return userService.findOrFail(userId);
    }
}
