package com.example.UserManagementWithDatabase.service;

import com.example.UserManagementWithDatabase.dao.PostRepository;
import com.example.UserManagementWithDatabase.dao.UserRepository;
import com.example.UserManagementWithDatabase.model.User;
import com.example.UserManagementWithDatabase.model.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {


    private final PostRepository postRepository;


    private final UserRepository userRepository;


    public Post savePost(Post post) {
        LocalDateTime created=  LocalDateTime.now();
        post.setCreatedOn(created);

        return postRepository.save(post);
    }

    public List<Post> getPostsOfUser(int userId) {
        Optional<User> user = userRepository.findById(userId);


        return user.get().getPosts();

    }

    public Post updatePost(Post postToUpdate, int id) {
        Post post;
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            post = optionalPost.get();
            post.setDescription(postToUpdate.getDescription());
            post.setTitle(postToUpdate.getTitle());
            post.setGeolocation(postToUpdate.getGeolocation());
            post.setTags(postToUpdate.getTags());

            LocalDateTime modified=  LocalDateTime.now();
            post.setModifiedOn(modified);
            postRepository.save(post);
        } else {
            return new Post();
        }
        return post;

    }


    public int voteUp( int id) {
        Post post;
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            post = optionalPost.get();
            int upVote = post.getUpVote();
            // int counter = post.getVoteCounter();
            upVote++;
            //counter++;

            // post.setVoteCounter(counter);
            post.setUpVote(upVote);
            postRepository.save(post);
            return upVote;


        } else {

            return 0;
        }


    }

    public int voteDown(int id) {
        Post post;
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            post = optionalPost.get();
            int downVote = post.getDownVote();

            downVote++;


            //post.setVoteCounter(counter);
            post.setDownVote(downVote);
            postRepository.save(post);
            return downVote;


        } else {

            return 0;
        }


    }


}
