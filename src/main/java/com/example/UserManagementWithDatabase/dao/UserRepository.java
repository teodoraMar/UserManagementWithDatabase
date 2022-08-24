package com.example.UserManagementWithDatabase.dao;

import com.example.UserManagementWithDatabase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
