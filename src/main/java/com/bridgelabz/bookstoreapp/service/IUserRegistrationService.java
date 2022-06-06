package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.entity.UserData;

import java.util.List;


public interface IUserRegistrationService {

    ResponseDTO registerUserInBookStore(UserDTO userDTO);

    ResponseDTO loginUser(UserLoginDTO userLoginDTO);

    ResponseDTO verifyEmailUsingOtp(Long otp);

    String deleteUserById(long id);
    UserData findUserById(long id);

    List<UserData> findAllUsers();
}
