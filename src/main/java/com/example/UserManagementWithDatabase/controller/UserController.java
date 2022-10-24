package com.example.UserManagementWithDatabase.controller;


import com.example.UserManagementWithDatabase.custom.exception.BusinessException;
import com.example.UserManagementWithDatabase.custom.exception.ControllerException;
import com.example.UserManagementWithDatabase.model.Factory.UserFactory;
import com.example.UserManagementWithDatabase.model.User;
import com.example.UserManagementWithDatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private final UserService userService;


    private final UserFactory userFactory;

    public UserController(UserService userService, UserFactory userFactory) {
        this.userService = userService;
        this.userFactory = userFactory;
    }


    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            User userSaved = userService.createUser(user);
            return new ResponseEntity<User>(userSaved, HttpStatus.CREATED);
        } catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ControllerException ce = new ControllerException("611", "Something went wrong in the control layer");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/factory")
    @ResponseBody
    public User createUser(@RequestParam String handle, @RequestParam String email, @RequestParam String username, @RequestParam String surname) {
        return userService.createUserWithFactory(handle, email, username, surname);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            ControllerException ce = new ControllerException("612", "Something went wrong in the control layer");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);

        }

    }


    @GetMapping("/user/{handle}")
    public ResponseEntity<?> getUserByHandle(@PathVariable String handle) {
        try {
            User user = userService.getUserByHandle(handle);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            ControllerException ce = new ControllerException("612", "Something went wrong in the control layer");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);

        }

    }


    @GetMapping(value = "/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getUsers();
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);

        } catch (BusinessException e) {
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            ControllerException ce = new ControllerException("613", "Something went wrong in the control layer");
            return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User userToUpdate, @PathVariable("id") int id) {

        return userService.updateUser(userToUpdate, id);
    }


    // @PutMapping(path="{handle}")
    //public User updateUserByHandle(@RequestBody User userToUpdate,@PathVariable("handle") String handle){

    // return userService.updateUser(userToUpdate,handle);
    // }

}
