package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.entity.UserData;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import org.apache.catalina.Store;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserRegistrationService implements IUserRegistrationService {

    @Autowired
    UserData userData;
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public ResponseDTO registerUserInBookStore(UserDTO userDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        UserData user = userRegistrationRepository.findUserDataByEmail(userDTO.getEmail());
        if(user==null){//check for user exists
            //maps the userdto with userdata class
            userData = modelMapper.map(userDTO,UserData.class);
           userData.setCreatedDate(LocalDate.now());
           //encrypting the password using password encoder
           String epassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
           userData.setPassword(epassword);
            System.out.println("password is "+epassword);
            userData = userRegistrationRepository.save(userData);
            responseDTO.setMessage("User Created successfully");
            responseDTO.setData(userData);
        }else {
            responseDTO.setMessage("user not created");
            responseDTO.setData("user with "+userDTO.getEmail()+" is already exists" );
        }
        return responseDTO;
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
