package com.example.controller;

import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.reposistory.CommentRepository;
import com.example.reposistory.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    public CommentController(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    // http://localhost:8080/api/v1/posts/{postId}/comments
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @RequestParam long postId) {
        Post post = postRepository.findById(postId).get();
        comment.setPost(post);
        Comment com = commentRepository.save(comment);
        return new ResponseEntity<>(com, HttpStatus.OK);
    }
}