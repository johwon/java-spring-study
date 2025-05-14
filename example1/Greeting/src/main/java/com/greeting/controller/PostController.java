package com.greeting.controller;

import com.greeting.model.Post;
import com.greeting.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getPost")
    public String getPost(Model model){
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "post";
    }

    @PostMapping("/addPost")
    public String addPost(@RequestParam String title, @RequestParam String content){
        postService.addPosts(title, content);
        return "redirect:/getPost";
    }
}
