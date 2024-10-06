package com.personal.petcare_backend.profiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.petcare_backend.profiles.exceptions.PostNotFoundException;
import com.personal.petcare_backend.profiles.models.Post;
import com.personal.petcare_backend.profiles.models.Profile;
import com.personal.petcare_backend.profiles.postdto.PostDto;
import com.personal.petcare_backend.profiles.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    private PostDto convertToDto(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getImageUrl());
    }

    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageUrl(postDto.getImageUrl());
        Post savedPost = repository.save(post);
        return convertToDto(savedPost);
    }

    public List<PostDto> getAllPosts() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<PostDto> getPostsByProfile(Profile profile) {
        return repository.findByProfile(profile).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PostDto getPostById(Long id) {
        Post post = repository.findById(id).orElseThrow();

        return convertToDto(post);
    }

    public PostDto updatePost(Long id, PostDto updatedPostDto) {
        Post existingPost = repository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

        existingPost.setTitle(updatedPostDto.getTitle());
        existingPost.setContent(updatedPostDto.getContent());
        existingPost.setImageUrl(updatedPostDto.getImageUrl());
        Post updatedPost = repository.save(existingPost);
        return convertToDto(updatedPost);
    }

    public void deletePost(Long id) {
        Post existingPost = repository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));
        repository.delete(existingPost);
    }
}