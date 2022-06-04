package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.entity.UserData;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegistrationService implements IUserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Override
    public UserData registerUserInBookStore(UserDTO userDTO) {
        UserData userData = new UserData(userDTO);
        return userRegistrationRepository.save(userData);
    }

    @Override
    public ResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<UserData> userList = userRegistrationRepository.findAll();
        UserData userDataByEmail = userRegistrationRepository.findUserDataByEmail(userLoginDTO.getEmail());
        if (userList.contains(userDataByEmail)) {
            String password = userDataByEmail.getPassword();
            if (password.equals(userLoginDTO.getPassword())) {
                responseDTO.setMessage("login SuccessFul");
                responseDTO.setData(userDataByEmail);
                return responseDTO;
            } else {
                responseDTO.setMessage("Sorry! login is unsuccessful");
                responseDTO.setData("Wrong password");
                return responseDTO;
            }

        }

        return new ResponseDTO("User not found!", "Wrong email");
    }
}
