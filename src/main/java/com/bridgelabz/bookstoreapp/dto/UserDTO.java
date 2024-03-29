package com.bridgelabz.bookstoreapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class UserDTO {
    @Pattern(regexp = "^[A-Z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", message = "please enter valid first name")
    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    public String firstName;
    @Pattern(regexp = "^[A-Z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", message = "please enter valid last name")
    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    public String lastName;

    @NotNull(message = "kyc should not be empty")
    public String kyc;

//    @NotEmpty(message = "dob cannot be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate dob;

    @Pattern(regexp = "^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "please do enter the valid email id")
    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "email cannot be empty")
    public String email;

    @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "please enter correct password")
    @NotNull(message = "Please Do Enter Password!")
    @NotEmpty(message = "Please Do  Enter Password!")
    public String password;


    public String role = "Seller";


    public UserDTO(String firstName, String lastName, String kyc, LocalDate dob, String email, String password, String role, String imageURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.kyc = kyc;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.role = role;

    }
}
