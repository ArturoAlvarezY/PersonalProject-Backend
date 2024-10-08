package com.personal.petcare_backend.users.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.petcare_backend.users.models.User;
import com.personal.petcare_backend.users.repository.UserRepository;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping(path = "/login")
    public ResponseEntity<Map<String, Object>> login() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        Map<String, Object> json = new HashMap<>();
        json.put("Message", "Logged");
        json.put("Username", authentication.getName());

        if (!authentication.getAuthorities().isEmpty()) {
            json.put("role", authentication.getAuthorities().iterator().next().toString());
        } else {
            json.put("role", "Unknown");
        }

        Optional<User> optionalUser = repository.findByUsername(authentication.getName());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            json.put("id", user.getId());
        } else {
            json.put("id", "Unknown");
        }
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }
}
