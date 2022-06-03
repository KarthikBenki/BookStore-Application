package com.bridgelabz.bookstoreapp.entity;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class UserData {
    private Long id;
    private String firstName;
    private String lastName;
    private String kyc;
    private LocalDate dob;
    private String email;
    private String password;

    public UserData(Long id, String firstName, String lastName, String kyc, LocalDate dob, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.kyc = kyc;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }


}
