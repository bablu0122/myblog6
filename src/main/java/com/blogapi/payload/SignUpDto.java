package com.blogapi.payload;

import lombok.Data;

@Data
public class SignUpDto {

    private String name;
    private long mobile;
    private String username;
    private String email;
    private String password;

}
