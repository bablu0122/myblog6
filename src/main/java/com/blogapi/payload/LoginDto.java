package com.blogapi.payload;

import lombok.Data;

import javax.persistence.Entity;



@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}

