package com.example.graphqljava.services;

import com.example.graphqljava.models.User;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {
    private static final List<User> users;

    static {
        User user1 = new User("Ram","ram@gmail.com");
        User user2 = new User("Vijay","vijay@gmail.com");
        User user3 = new User("aman","aman@gmail.com");
        users =  java.util.List.of(user1, user2,user3);
    }

    public User getUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    public List<User> getAllUsers() {
        return users;
    }
}
