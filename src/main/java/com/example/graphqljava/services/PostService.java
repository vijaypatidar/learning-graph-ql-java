package com.example.graphqljava.services;

import com.example.graphqljava.models.Comment;
import com.example.graphqljava.models.Post;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostService {
    private static final List<Post> POSTS = new ArrayList<>();
    private static int i=0;
    static {
        Comment comment1 = new Comment();
        comment1.setUserId("vijay");
        comment1.setDateTime(LocalDateTime.now());

        Post post = new Post();
        post.setPostId("gv1");
        post.setUserId("vijay");
        post.setTitle("Give and Take");
        post.setDescription("Book by Adam Grant");
        post.setComments(List.of(comment1));
        POSTS.add(post);
    }

    public List<Post> getPosts() {
        return POSTS;
    }

    public Post addPost(Post post) {
        POSTS.add(post);
        post.setPostId((i++)+"");
        return post;
    }

    public String deletePost(String postId){
        Optional<Post> optionalPost = POSTS.stream()
                .filter(post -> post.getPostId().equals(postId))
                .findFirst();

        if (optionalPost.isPresent()){
            POSTS.remove(optionalPost.get());
            return "post deleted";
        }else {
            return "post not found";
        }
    }

    public Post getPostById(String postId) {
        return POSTS.stream().filter(post -> post.getPostId().equals(postId)).findFirst().orElse(null);
    }
}
