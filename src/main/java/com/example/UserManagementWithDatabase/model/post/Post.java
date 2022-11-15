package com.example.UserManagementWithDatabase.model.post;


import com.example.UserManagementWithDatabase.model.BaseEntity;
import com.example.UserManagementWithDatabase.util.JsonUtil;
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

public class Post extends BaseEntity {


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

    public void setGeolocation(long lon, long lat) {
        this.geolocation = String.valueOf(JsonUtil.objectToJson(geolocation));
    }


    public Geolocation getGeolocation(Class<Geolocation> geolocationClass) {
        return (Geolocation) JsonUtil.fromJson(geolocation, geolocationClass);
    }
}
