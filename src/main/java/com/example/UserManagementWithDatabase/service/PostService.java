package com.example.UserManagementWithDatabase.service;

import com.example.UserManagementWithDatabase.custom.exception.BusinessException;
import com.example.UserManagementWithDatabase.dao.PostRepository;
import com.example.UserManagementWithDatabase.dao.UserRepository;
import com.example.UserManagementWithDatabase.model.Factory.PostFactory;
import com.example.UserManagementWithDatabase.model.PostDTO;
import com.example.UserManagementWithDatabase.model.post.Geolocation;
import com.example.UserManagementWithDatabase.model.post.Post;
import com.example.UserManagementWithDatabase.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {
    private String geolocation;
    private JsonUtil jsonUtil;

    private final PostRepository postRepository;


    private final UserRepository userRepository;


    public Post savePost(Post post) {

        if (userRepository.findById(post.getUser()).isEmpty()) {
            throw new BusinessException("601", "Oops!User does not exist in the database, cannot save post!");
        }
        try {
            LocalDateTime created = LocalDateTime.now();
            post.setCreatedOn(created);


            return postRepository.save(post);
        } catch (Exception e) {
            throw new BusinessException("602", "Something went wrong in the service layer" + e.getMessage());
        }
    }

    public Post createPostWithFactory(String description, String title, String geolocation, int user) {
        Post post = PostFactory.createPost(description, title, geolocation, user);
        postRepository.save(post);
        return post;

    }

    public List<Post> getPostsOfUser(int userId) {
        if (userRepository.findById(userId).isEmpty())
            throw new BusinessException("605", "Post list is empty! Nothing to show, because user does not exist!");
        try {


            List<Post> posts = userRepository.findById(userId).get().getPosts();
            return posts;
        } catch (Exception e) {
            throw new BusinessException("606", "Something went wrong in the service layer while fetching for all posts" + e.getMessage());
        }

    }


    public Post updatePost(Post postToUpdate, int id) {
        Post post;
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) throw new IllegalArgumentException("Post does not exist");


        post = optionalPost.get();
        post.setDescription(postToUpdate.getDescription());
        post.setTitle(postToUpdate.getTitle());
        post.setGeolocation(postToUpdate.getGeolocation());
        post.setTags(postToUpdate.getTags());

        LocalDateTime modified = LocalDateTime.now();
        post.setModifiedOn(modified);
        postRepository.save(post);

        return post;

    }


    @Transactional
    public int voteUp(int id) {
        Post post;
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) throw new NoSuchElementException("Invalid post");
        post = optionalPost.get();
        int upVote = post.getUpVote();
        // int counter = post.getVoteCounter();
        upVote++;
        //counter++;

        // post.setVoteCounter(counter);
        post.setUpVote(upVote);
        postRepository.save(post);
        return upVote;


    }

    @Transactional
    public int voteDown(int id) {
        Post post;
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) throw new NoSuchElementException("Invalid post");
        post = optionalPost.get();
        int downVote = post.getDownVote();

        downVote++;


        //post.setVoteCounter(counter);
        post.setDownVote(downVote);
        postRepository.save(post);
        return downVote;


    }

    public PostDTO convertEntityToDTO(Post post) {
        PostDTO postDTO = new PostDTO();

        postDTO.setDescription(post.getDescription());
        postDTO.setGeolocation(post.getGeolocation());
        postDTO.setTags(post.getTags());
        return postDTO;
    }

    public Post convertDTOToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setDescription(postDTO.getDescription());
        post.setTags(postDTO.getTags());
        post.setTitle(postDTO.getTitle());
        post.setGeolocation(postDTO.getGeolocation());
        return post;
    }


    public void setGeolocation(long lon, long lat) {
        this.geolocation = String.valueOf(JsonUtil.objectToJson(geolocation));
    }


    public Geolocation getGeolocation(Class<Geolocation> geolocationClass) {
        return (Geolocation) JsonUtil.fromJson(geolocation, geolocationClass);
    }


}


