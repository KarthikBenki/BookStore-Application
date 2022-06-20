package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;


@Data
public class UserLoginDTO {

    private String email;
    private String password;

    private String role;

    public UserLoginDTO(String email, String password,String role) {
        this.email = email;
        this.password = password;
        this.role=role;
    }
}
