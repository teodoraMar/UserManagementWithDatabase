package com.example.UserManagementWithDatabase.service;


import com.example.UserManagementWithDatabase.custom.exception.BusinessException;
import com.example.UserManagementWithDatabase.dao.PostRepository;
import com.example.UserManagementWithDatabase.dao.UserRepository;
import com.example.UserManagementWithDatabase.model.Factory.UserFactory;
import com.example.UserManagementWithDatabase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
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
        if (userRepository.findByHandle(user.getHandle()).isPresent()) {
            throw new BusinessException("601", "Oops!User already exists in the database");
        }
        try {
            LocalDateTime created = LocalDateTime.now();
            user.setCreatedOn(created);
            return userRepository.save(user);


        } catch (Exception e) {


            throw new BusinessException("602", "Something went wrong in the service layer" + e.getMessage());
        }

    }

    public User createUserWithFactory(String handle, String email, String username, String surname) {
        UserFactory userFactory = new UserFactory();
        User user = UserFactory.createUser(handle, email, username, surname);
        userRepository.save(user);
        return user;
    }

    public User getUserById(int id) {
        try {
            User user = userRepository.findById(id).get();
            if (user.getId() < 1) throw new BusinessException("603", "User does not exist");
            return user;
        } catch (NoSuchElementException e) {
            throw new BusinessException("604", "User with given id does not exist in the database");
        }
    }


    public List<User> getUsers() {
        try {
            List<User> userList = userRepository.findAll();
            if (userList.isEmpty())
                throw new BusinessException("605", "User list is empty, nothing to show");
            return userList;
        } catch (Exception e) {
            throw new BusinessException("606", "Something went wrong in the service layer while fetching for all posts" + e.getMessage());
        }

    }

    public User getUserByHandle(String handle) {
        try {
            User user = userRepository.findByHandle(handle).get();
            if (user.getHandle().isEmpty()) throw new BusinessException("607", "User does not exist");
            return user;
        } catch (NoSuchElementException e) {
            throw new BusinessException("608", "User with given handle does not exist in the database");
        }
    }

    public User updateUserByHandle(User userToUpdate, String handle) {
        User user;
        Optional<User> optionalUser = userRepository.findByHandle(handle);
        Optional.ofNullable(optionalUser).orElseThrow(() -> new IllegalArgumentException("Invalid user, handle is invalid"));

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
        User optionalUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No user found, ID does not exist in database"));

        user = optionalUser;
        user.setUsername(userToUpdate.getUsername());
        user.setEmail(userToUpdate.getEmail());
        user.setHandle(userToUpdate.getHandle());
        user.setSurname(userToUpdate.getSurname());
        user.setCountry(userToUpdate.getCountry());
        user.setCity(userToUpdate.getCity());

        LocalDateTime modified = LocalDateTime.now();
        user.setModifiedOn(modified);
        userRepository.save(user);

        return user;

    }


}
