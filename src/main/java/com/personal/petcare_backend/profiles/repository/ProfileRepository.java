package com.personal.petcare_backend.profiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.petcare_backend.profiles.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    
}
