package com.personal.petcare_backend.profiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.petcare_backend.profiles.exceptions.PostNotFoundException;
import com.personal.petcare_backend.profiles.models.Post;
import com.personal.petcare_backend.profiles.models.Profile;
import com.personal.petcare_backend.profiles.postdto.PostDto;
import com.personal.petcare_backend.profiles.repository.PostRepository;
import com.personal.petcare_backend.profiles.repository.ProfileRepository;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository Postrepository;

    @Autowired
    private ProfileRepository profileRepository;

    private PostDto convertToDto(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getImageUrl());
    }

    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageUrl(postDto.getImageUrl());
        Post savedPost = Postrepository.save(post);
        return convertToDto(savedPost);
    }

    public List<PostDto> getAllPosts() {
        return Postrepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<PostDto> getPostsByProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new PostNotFoundException("Profile not found with id: " + profileId));

        return Postrepository.findByProfile(profile).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PostDto getPostById(Long id) {
        Post post = Postrepository.findById(id).orElseThrow();

        return convertToDto(post);
    }

    public PostDto updatePost(Long id, PostDto updatedPostDto, Long userId) throws AccessDeniedException {
        Post existingPost = Postrepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));

        Profile profile = existingPost.getProfile(); 
        if (profile == null || profile.getUser() == null || !profile.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You do not have permission to edit this post.");
        }
        existingPost.setContent(updatedPostDto.getContent());
        existingPost.setTitle(updatedPostDto.getTitle());
        existingPost.setImageUrl(updatedPostDto.getImageUrl()); 
        Postrepository.save(existingPost);
        
        return convertToDto(existingPost); 
    }    
    
    public void deletePost(Long id) {
        Post existingPost = Postrepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + id));
        Postrepository.delete(existingPost);
    }
}
