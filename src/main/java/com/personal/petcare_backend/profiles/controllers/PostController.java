package com.personal.petcare_backend.profiles.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.personal.petcare_backend.profiles.exceptions.PostNotFoundException;
import com.personal.petcare_backend.profiles.postdto.PostDto;
import com.personal.petcare_backend.profiles.service.PostService;
import com.personal.petcare_backend.users.models.User;
import com.personal.petcare_backend.users.repository.UserRepository;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService service;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @RequestParam Long userId) {
        PostDto savedPost = service.createPost(postDto, userId);

        if (savedPost == null) {
            throw new PostNotFoundException("The post cant be saved");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @GetMapping("/allposts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = service.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<PostDto>> getPostsByProfile(@PathVariable Long profileId) {
        List<PostDto> posts = service.getPostsByProfile(profileId);
        
        if (posts == null) {
            throw new PostNotFoundException("Post not found in this profileId: " + profileId);
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        PostDto postDto = service.getPostById(id);

        if (postDto == null) {
            throw new PostNotFoundException("The post by that id doesnt exist: " + id);
        }
        return ResponseEntity.ok(postDto);
    }

    @PutMapping("/{id}/{userId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto postDto,
            @PathVariable Long userId) throws AccessDeniedException {
        PostDto updatedPost = service.updatePost(id, postDto, userId);

        if (updatedPost == null) {
            throw new PostNotFoundException("The update cant be uploaded");
        }
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        service.deletePost(id, user.getId());
        return ResponseEntity.noContent().build();
    }
}