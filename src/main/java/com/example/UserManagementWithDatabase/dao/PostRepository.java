package com.example.UserManagementWithDatabase.dao;

import com.example.UserManagementWithDatabase.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {


}
