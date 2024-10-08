package com.personal.petcare_backend.profiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.personal.petcare_backend.users.models.User;
import com.personal.petcare_backend.profiles.exceptions.PostNotFoundException;
import com.personal.petcare_backend.profiles.models.Post;
import com.personal.petcare_backend.profiles.postdto.PostDto;
import com.personal.petcare_backend.profiles.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.personal.petcare_backend.users.repository.UserRepository;

import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    
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

    public PostDto createPost(PostDto postDto, Long adminId) {
        User user = userRepository.findById(adminId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                if (!user.getId().equals(1L)) {
                    throw new AccessDeniedException("Only admins can create posts.");
        }

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

    public Page<PostDto> getAllPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(this::convertToDto);
    }

    public PostDto updatePost(Long postId, PostDto postDto, Long adminId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        User user = userRepository.findById(adminId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!user.getId().equals(1L)) {
            throw new AccessDeniedException("Only admins can update posts.");
        }

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageUrl(postDto.getImageUrl());

        Post updatedPost = postRepository.save(post);
        return convertToDto(updatedPost);
    }

    public void deletePost(Long postId, Long adminId) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        User user = userRepository.findById(adminId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                if (!user.getId().equals(1L)) {
                    throw new AccessDeniedException("Only admins can delete posts.");
        }

        postRepository.delete(existingPost);
    }
}
