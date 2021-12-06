package com.example.graphqljava.graphql;

import com.example.graphqljava.models.Post;
import com.example.graphqljava.models.User;
import com.example.graphqljava.services.PostService;
import com.example.graphqljava.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MyDataFetchers {
    private final UserService userService;
    private final PostService postService;
    private final ObjectMapper objectMapper;

    public DataFetcher<List<User>> getUsersDataFetcher() {
        return env -> userService.getAllUsers();
    }

    public DataFetcher<User> getUserDataFetcher() {
        return env -> {
            String emailArg = env.getArgument("email");
            return userService.getUserByEmail(emailArg);
        };
    }

    public DataFetcher<User> getPostDataFetcher() {
        return env -> {
            String emailArg = env.getArgument("postId");
            return userService.getUserByEmail(emailArg);
        };
    }

    public DataFetcher<List<Post>> getPostsDataFetcher() {
        return env -> postService.getPosts();
    }

    public DataFetcher<Post> getAddPostDataFetcher() {
        return env->{
            Map<String,Object> postMap = env.getArgument("post");
            Post post = objectMapper.convertValue(postMap,Post.class);
            return postService.addPost(post);
        };
    }
    public DataFetcher<String> getDeletePostDataFetcher() {
        return env->{
            String postIdArg = env.getArgument("postId");
            return postService.deletePost(postIdArg);
        };
    }
}
