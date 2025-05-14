package com.greeting.service;

import com.greeting.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();

    public List<Post> getAllPosts(){
        return posts;
    }

    public void addPosts(String title, String content){
        posts.add(new Post(title, content));
    }
}
