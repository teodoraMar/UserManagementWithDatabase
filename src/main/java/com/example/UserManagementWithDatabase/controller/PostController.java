package com.example.UserManagementWithDatabase.controller;

import com.example.UserManagementWithDatabase.custom.exception.BusinessException;
import com.example.UserManagementWithDatabase.custom.exception.ControllerException;
import com.example.UserManagementWithDatabase.model.post.Post;
import com.example.UserManagementWithDatabase.service.PostService;
import com.example.UserManagementWithDatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        {
            try {
                Post postSaved = postService.savePost(post);
                return new ResponseEntity<Post>(postSaved, HttpStatus.CREATED);
            }catch(BusinessException e){
                ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
                return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
            }catch(Exception e){
                ControllerException ce = new ControllerException("611","Something went wrong in the control layer");
                return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
            }

        }


    }
   @GetMapping("/posts/{id}")
   public ResponseEntity<?> getPosts(@PathVariable int id ){
        try{
       List<Post> posts=postService.getPostsOfUser(id);
       return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);

   }catch(BusinessException e){
       ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
       return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);

   }catch(Exception e){
       ControllerException ce = new ControllerException("612","Something went wrong in the control layer");
       return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);

   }


   }

    @PutMapping( "/update/{id}")
    public Post updatePost(@RequestBody Post postToUpdate, @PathVariable("id") int id)  {

        return postService.updatePost(postToUpdate, id);
    }

    @PutMapping(path = "{id}/voteUp")
    public int voteUp(@PathVariable int id)  {
        return postService.voteUp( id);
    }

    @PutMapping(path = "{id}/voteDown")
    public int voteDown( @PathVariable int id) {
        return postService.voteDown( id);
    }


}
