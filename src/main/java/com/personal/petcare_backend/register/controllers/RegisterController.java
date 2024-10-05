package com.personal.petcare_backend.register.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.petcare_backend.register.services.RegisterServices;
import com.personal.petcare_backend.users.dtos.UserDto;

@RestController
@RequestMapping(path = "/api/v1/register")
public class RegisterController {

    private final RegisterServices service;

    @Autowired
    public RegisterController(RegisterServices service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestHeader("username") String username,
            @RequestHeader("password") String password) {
        try {
            UserDto newUser = new UserDto();
            newUser.setUsername(username);
            newUser.setPassword(password);
            service.registerUser(newUser);
            return ResponseEntity.ok("User registered!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error registering user!" + e.getMessage());
        }
    }
}