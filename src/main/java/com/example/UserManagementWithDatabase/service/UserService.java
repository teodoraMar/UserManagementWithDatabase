package com.example.UserManagementWithDatabase.service;


import com.example.UserManagementWithDatabase.dao.UserRepository;
import com.example.UserManagementWithDatabase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User userToUpdate, int id){
        User user=null;
        Optional<User> optionalUser=userRepository.findById(id);
        if(optionalUser.isPresent()){
            user=optionalUser.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setName(user.getName());
            userToUpdate.setSurname(user.getSurname());
            userToUpdate.setCountry(user.getCountry());
            userToUpdate.setCity(user.getCity());
        } else{
            return new User();
        }
        return userToUpdate;

    }

}
