package com.example.UserManagementWithDatabase.model.post;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post")

public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column

    private Integer user;


    @NotBlank
    @Column
    private String title;

    @NotBlank
    @Column
    private String description;


    @Column(name = "geolocation", columnDefinition = "json")
    private String geolocation;


    @Enumerated(EnumType.STRING)
    @Column
    private Tags tags;


    @Column(name = "upvote")
    private int upVote;
    @Column(name = "downvote")
    private int downVote;
    //private int voteCounter;
}
