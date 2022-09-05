package com.example.UserManagementWithDatabase.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table
public class User {
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
}
