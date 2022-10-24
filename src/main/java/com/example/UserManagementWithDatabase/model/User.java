package com.example.UserManagementWithDatabase.model;


import com.example.UserManagementWithDatabase.model.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "user")

public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Email
    @Column
    private String email;
    @NotBlank
    @Column
    private String username;
    @NotBlank
    @Column
    private String handle;
    @Column
    private String surname;
    @Column
    private String country;
    @Column
    private String city;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
