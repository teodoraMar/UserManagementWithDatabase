package com.example.UserManagementWithDatabase.model.Factory;


import com.example.UserManagementWithDatabase.model.post.Post;

import java.time.LocalDateTime;

public class PostFactory {
    public static Post createPost(String description, String title, String geolocation, int user) {


        Post post = new Post();

        post.setTitle(title);
        post.setUser(user);
        LocalDateTime created = LocalDateTime.now();
        post.setGeolocation(geolocation);
        post.setDescription(description);
        post.setCreatedOn(created);
        return post;
    }

}
