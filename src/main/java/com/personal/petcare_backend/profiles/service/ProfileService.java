package com.personal.petcare_backend.profiles.service;


import com.personal.petcare_backend.profiles.models.Profile;
import com.personal.petcare_backend.profiles.repository.ProfileRepository;
import com.personal.petcare_backend.users.models.User;
import com.personal.petcare_backend.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public Profile getProfileByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getProfile).orElse(null);
    }

    public Profile getProfileByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(User::getProfile).orElse(null);
    }
}
