package com.example.UserManagementWithDatabase.controller;

import com.example.UserManagementWithDatabase.model.post.Post;
import com.example.UserManagementWithDatabase.service.PostService;
import com.example.UserManagementWithDatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/post")
public class PostController {


    @Autowired
    private final PostService postService;

    @Autowired
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;

        this.userService = userService;
    }


    @PostMapping
    public Post addPost(@RequestBody Post post) {
        return postService.savePost(post);

    }


    @PutMapping(path = "{id}/voteUp")
    public int voteUp(@RequestBody Post post, @PathVariable int id) {
        return postService.voteUp(post, id);
    }

    @PutMapping(path = "{id}/voteDown")
    public int voteDown(@RequestBody Post post, @PathVariable int id) {
        return postService.voteDown(post, id);
    }


}
