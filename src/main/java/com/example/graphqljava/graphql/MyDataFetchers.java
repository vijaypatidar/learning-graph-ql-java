package com.example.graphqljava.graphql;

import com.example.graphqljava.models.User;
import com.example.graphqljava.services.UserService;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MyDataFetchers {
    private final UserService userService;

    public DataFetcher<List<User>> getUsersDataFetcher() {
        return env -> userService.getAllUsers();
    }

    public DataFetcher<User> getUserDataFetcher() {
        return env -> {
            String emailArg = env.getArgument("email");
            return userService.getUserByEmail(emailArg);
        };
    }
}
