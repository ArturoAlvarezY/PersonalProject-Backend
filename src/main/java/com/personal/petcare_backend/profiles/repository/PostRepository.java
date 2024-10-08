package com.personal.petcare_backend.profiles.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.petcare_backend.profiles.models.Post;
import com.personal.petcare_backend.profiles.models.Profile;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByProfile(Profile profile);
    Page<Post> findAll(Pageable pageable);

}

