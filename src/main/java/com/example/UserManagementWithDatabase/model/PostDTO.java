package com.example.UserManagementWithDatabase.model;

import com.example.UserManagementWithDatabase.model.post.Tags;
import lombok.Data;

@Data
public class PostDTO {


    private int user;
    private String title;
    private String geolocation;

    private String description;
    private Tags tags;


}
