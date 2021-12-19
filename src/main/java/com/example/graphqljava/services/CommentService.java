package com.example.graphqljava.services;

import com.example.graphqljava.models.Comment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final static List<Comment> comments = new ArrayList<>();


    public List<Comment> getComments(String postId) {
        return List.of();
    }
}
