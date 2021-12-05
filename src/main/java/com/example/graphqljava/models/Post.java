package com.example.graphqljava.models;

import lombok.Data;

import java.util.List;

@Data
public class Post {
    private String title, description, userId, noteId;
    private List<Comment> comments;
}
