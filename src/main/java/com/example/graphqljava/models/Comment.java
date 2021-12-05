package com.example.graphqljava.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private String userId;
    private LocalDateTime dateTime;

}
