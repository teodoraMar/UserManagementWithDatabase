package com.example.UserManagementWithDatabase.model.Factory;


import com.example.UserManagementWithDatabase.dao.UserRepository;
import com.example.UserManagementWithDatabase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserFactory {

    @Autowired
    private User user;
    @Autowired
    private UserRepository userRepository;

    public User createUser(String handle,String email,String username,String surname){


       if(userRepository.findByHandle(handle).isPresent()){
           throw new IllegalArgumentException("User already exists");
       }
       LocalDateTime created = LocalDateTime.now();
       user.setHandle(handle);
       user.setEmail(email);
       user.setUsername(username);
       user.setSurname(surname);


       user.setCreatedOn(created);
       userRepository.save(user);
       return user;
    }




}
