package com.personal.petcare_backend.profiles.controllers;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.personal.petcare_backend.profiles.exceptions.PostNotFoundException;
import com.personal.petcare_backend.profiles.postdto.PostDto;
import com.personal.petcare_backend.profiles.service.PostService;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto savedPost = service.createPost(postDto);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(savedPost);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = service.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<PostDto>> getPostsByProfile(@PathVariable Long profileId) {
        List<PostDto> posts = service.getPostsByProfile(profileId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        PostDto postDto = service.getPostById(id);
        if (postDto != null) {
            throw new PostNotFoundException("Post not found with id: " + id);
        }
        return ResponseEntity.ok(postDto);
    }

    @PutMapping("/{id}/{userId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto postDto,
            @PathVariable Long userId) throws AccessDeniedException {
        PostDto updatedPost = service.updatePost(id, postDto, userId);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        service.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}