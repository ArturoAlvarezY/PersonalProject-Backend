package com.personal.petcare_backend.profiles.controllers;

import com.personal.petcare_backend.profiles.models.Profile;
import com.personal.petcare_backend.profiles.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<Profile> getCurrentUserProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = profileService.getProfileByUsername(username);

        if (profile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profile);
    }
}