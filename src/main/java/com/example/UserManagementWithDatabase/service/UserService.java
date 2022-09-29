package com.example.UserManagementWithDatabase.service;


import com.example.UserManagementWithDatabase.dao.PostRepository;
import com.example.UserManagementWithDatabase.dao.UserRepository;
import com.example.UserManagementWithDatabase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PostRepository postRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByHandle(String handle) {
        List<User> users = userRepository.findAll();
        User newUser = null;
        for (User user : users) {
            if (user.getHandle().equals(handle)) {
                newUser = user;

            }
        }
        return newUser;
    }

    public User updateUserByHandle(User userToUpdate, String handle) {
        User user;
        Optional<User> optionalUser = userRepository.findByHandle(handle);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setHandle(user.getHandle());
            userToUpdate.setSurname(user.getSurname());
            userToUpdate.setCountry(user.getCountry());
            userToUpdate.setCity(user.getCity());
        } else {
            return new User();
        }
        return userToUpdate;

    }

    public User updateUser(User userToUpdate, int id) {
        User user;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            user.setUsername(userToUpdate.getUsername());
            user.setEmail(userToUpdate.getEmail());
            user.setHandle(userToUpdate.getHandle());
            user.setSurname(userToUpdate.getSurname());
            user.setCountry(userToUpdate.getCountry());
            user.setCity(userToUpdate.getCity());
            userRepository.save(user);
        } else {
            return new User();
        }
        return user;

    }


}
