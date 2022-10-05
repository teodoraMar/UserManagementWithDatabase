package com.example.UserManagementWithDatabase.controller;

import com.example.UserManagementWithDatabase.model.post.Post;
import com.example.UserManagementWithDatabase.service.PostService;
import com.example.UserManagementWithDatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
   @GetMapping("/posts/{id}")
   public List<Post> getPosts(@PathVariable int id ){
       return postService.getPostsOfUser(id);
   }

    @PutMapping( "/update/{id}")
    public Post updatePost(@RequestBody Post postToUpdate, @PathVariable("id") int id) {

        return postService.updatePost(postToUpdate, id);
    }

    @PutMapping(path = "{id}/voteUp")
    public int voteUp(@PathVariable int id) {
        return postService.voteUp( id);
    }

    @PutMapping(path = "{id}/voteDown")
    public int voteDown( @PathVariable int id) {
        return postService.voteDown( id);
    }


}
