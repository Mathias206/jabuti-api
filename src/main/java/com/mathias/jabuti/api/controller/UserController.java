package com.mathias.jabuti.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mathias.jabuti.domain.model.User;
import com.mathias.jabuti.domain.repository.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository repository;



    @GetMapping
    private List<User> getUsers() {
        return repository.findAll();
    }

}
