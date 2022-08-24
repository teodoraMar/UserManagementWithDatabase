package com.example.UserManagementWithDatabase.controller;


import com.example.UserManagementWithDatabase.model.User;
import com.example.UserManagementWithDatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.createUser(user);

    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getUsers();

    }

    @PutMapping(path="{id}")
    public User updateUser(@RequestBody User userToUpdate,@PathVariable("id") int id){
        return userService.updateUser(userToUpdate,id);
    }
}
