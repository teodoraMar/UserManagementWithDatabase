package com.example.UserManagementWithDatabase.dao;

import com.example.UserManagementWithDatabase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByHandle(String handle);

}

