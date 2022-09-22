package com.example.UserManagementWithDatabase.service;

import com.example.UserManagementWithDatabase.dao.PostRepository;
import com.example.UserManagementWithDatabase.dao.UserRepository;
import com.example.UserManagementWithDatabase.model.User;
import com.example.UserManagementWithDatabase.model.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {


    private final PostRepository postRepository;


    private final UserRepository userRepository;


    public Post savePost(Post post) {


        return postRepository.save(post);
    }

    public List<Post> getPostsOfUser(int userId) {
        Optional<User> user = userRepository.findById(userId);


        return user.get().getPosts();

    }


    public int voteUp(Post postToUpdate, int id) {
        Post post;
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            post = optionalPost.get();
            int upVote = post.getUpVote();
            // int counter = post.getVoteCounter();
            upVote++;
            //counter++;

            // post.setVoteCounter(counter);
            return upVote;


        } else {

            return 0;
        }


    }

    public int voteDown(Post postToUpdate, int id) {
        Post post;
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            post = optionalPost.get();
            int downVote = post.getDownVote();

            downVote++;


            //post.setVoteCounter(counter);
            post.setDownVote(downVote);
            return downVote;


        } else {

            return 0;
        }


    }


}
