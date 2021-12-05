package com.example.graphqljava.services;

import com.example.graphqljava.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private static final List<User> users;

    static {
        User user = new User();
        user.setUserId("vijay");
        user.setName("VIJAY");
        user.setEmail("vijay@gmail.com");
        users = java.util.List.of(user);
    }

    public User getUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    public List<User> getAllUsers() {
        return users;
    }
}
