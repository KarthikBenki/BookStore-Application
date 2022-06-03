package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.entity.UserData;


public interface IUserRegistrationService {

    UserData registerUserInBookStore(UserDTO userDTO);
}
