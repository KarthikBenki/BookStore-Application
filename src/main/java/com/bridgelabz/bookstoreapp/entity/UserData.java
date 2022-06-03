package com.bridgelabz.bookstoreapp.entity;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@ToString
@Entity
@Table(name = "user_registration")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public UserData(UserDTO userDTO) {
        this.updateUserData(userDTO);
    }

    public UserData() {
    }

    private void updateUserData(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.kyc = userDTO.getKyc();
        this.dob = userDTO.getDob();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
    }
}
