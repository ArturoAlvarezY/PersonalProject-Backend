package com.personal.petcare_backend.profiles.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.petcare_backend.profiles.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
