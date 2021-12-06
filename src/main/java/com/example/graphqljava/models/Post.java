package com.example.graphqljava.models;

import lombok.Data;

import java.util.List;

@Data
public class Post {
    private String title, description, userId, postId;
    private List<Comment> comments;
}
