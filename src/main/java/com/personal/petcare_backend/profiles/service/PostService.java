package com.personal.petcare_backend.profiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.personal.petcare_backend.users.models.User;
import com.personal.petcare_backend.profiles.exceptions.PostNotFoundException;
import com.personal.petcare_backend.profiles.models.Post;
import com.personal.petcare_backend.profiles.models.Profile;
import com.personal.petcare_backend.profiles.postdto.PostDto;
import com.personal.petcare_backend.profiles.repository.PostRepository;
import com.personal.petcare_backend.profiles.repository.ProfileRepository;
import com.personal.petcare_backend.users.exceptions.UserNameNotFoundException;
import com.personal.petcare_backend.users.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;

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

    private boolean hasEditPermission(Post post, User user) {
        return (post.getProfile() != null && post.getProfile().getId().equals(user.getId())) || user.getId().equals(1L);
    }

    public PostDto updatePost(Long postId, PostDto postDto, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNameNotFoundException("User not found"));

        if (!hasEditPermission(post, user)) {
            throw new AccessDeniedException("You do not have permission to edit this post.");
        }

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        PostDto updatedPostDto = new PostDto();
        updatedPostDto.setId(updatedPost.getId());
        updatedPostDto.setTitle(updatedPost.getTitle());
        updatedPostDto.setContent(updatedPost.getContent());

        return updatedPostDto;
    }

    public void deletePost(Long postId, Long userId) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!hasEditPermission(existingPost, user)) {
            throw new AccessDeniedException("You do not have permission to delete this post.");
        }
}
}