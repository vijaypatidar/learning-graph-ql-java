package com.example.graphqljava.services;

import com.example.graphqljava.models.Comment;
import com.example.graphqljava.models.Post;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private static final List<Post> POSTS = new ArrayList<>();

    static {
        Comment comment1 = new Comment();
        comment1.setUserId("vijay");
        comment1.setDateTime(LocalDateTime.now());

        Post post = new Post();
        post.setUserId("vijay");
        post.setTitle("Give and Take");
        post.setDescription("Book by Adam Grant");
        post.setComments(List.of(comment1));
        POSTS.add(post);
    }

    public List<Post> getPosts() {
        return POSTS;
    }
}
