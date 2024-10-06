package com.personal.petcare_backend.profiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.personal.petcare_backend.profiles.exceptions.PostNotFoundException;
import com.personal.petcare_backend.profiles.models.Post;
import com.personal.petcare_backend.profiles.models.Profile;
import com.personal.petcare_backend.profiles.postdto.PostDto;
import com.personal.petcare_backend.profiles.repository.PostRepository;
import com.personal.petcare_backend.profiles.repository.ProfileRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private  ProfileRepository profileRepository;
    
    private PostDto convertToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setImageUrl(post.getImageUrl());
        return postDto;
    }

    public PostDto createPost(PostDto postDto, Long userId) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageUrl(postDto.getImageUrl());
        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost);
    }

    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));
        return convertToDto(post);
    }

    public List<PostDto> getPostsByProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new PostNotFoundException("Profile not found with id: " + profileId));
        
        List<Post> posts = postRepository.findByProfile(profile);
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void deletePost(Long id, Long userId) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

        Profile profile = existingPost.getProfile();
        if (profile == null || profile.getUser() == null || !profile.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You do not have permission to delete this post.");
        }

        postRepository.delete(existingPost);
    }

    public PostDto updatePost(Long id, PostDto updatedPostDto, Long userId) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

        Profile profile = existingPost.getProfile();
        if (profile == null || profile.getUser() == null || !profile.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You do not have permission to edit this post.");
        }

        existingPost.setContent(updatedPostDto.getContent());
        existingPost.setTitle(updatedPostDto.getTitle());
        existingPost.setImageUrl(updatedPostDto.getImageUrl());
        
        postRepository.save(existingPost);

        return convertToDto(existingPost);
    }

}