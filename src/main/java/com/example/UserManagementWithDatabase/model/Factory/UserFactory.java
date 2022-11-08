package com.example.UserManagementWithDatabase.model.Factory;

import com.example.UserManagementWithDatabase.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class UserFactory {


    public static User createUser(String handle, String email, String username, String surname) {


        User user = new User();
        LocalDateTime created = LocalDateTime.now();
        user.setHandle(handle);
        user.setEmail(email);
        user.setUsername(username);
        user.setSurname(surname);
        user.setCreatedOn(created);
        return user;
    }


}
