package com.example.UserManagementWithDatabase.model.post;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Geolocation {


    private long lon;
    private long lat;


}
