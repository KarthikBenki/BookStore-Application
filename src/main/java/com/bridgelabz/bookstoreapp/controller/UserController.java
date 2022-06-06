package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.entity.UserData;
import com.bridgelabz.bookstoreapp.service.EmailSenderService;
import com.bridgelabz.bookstoreapp.service.IUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/bookstoreApi")
public class UserController {


    @Autowired
    private EmailSenderService senderService;

    @Autowired
    private IUserRegistrationService userRegistrationService;

    /**
     * @return A Welcome message to test the api
     * @Purpose Testing the Api
     */
    @GetMapping({"/welcome", "/", ""})
    public String welcomeApi() {
        return "Welcome To BookStore App";
    }


    /**
     * @return status of email
     * @Purpose To check if the test mail is sending or not
     */
    @GetMapping("/email/{toEmail}/{subject}/{body}")
    public ResponseEntity<ResponseDTO> sendTestEmail(@PathVariable String toEmail,
                                                     @PathVariable String subject,
                                                     @PathVariable String body) {
        senderService.sendEmail(toEmail,
                subject,
                body);
        ResponseDTO responseDTO = new ResponseDTO("Sent Email Successfully", toEmail);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * @return status of email
     * @throws MessagingException
     */
    @GetMapping("/emailAttached")
    public ResponseEntity<ResponseDTO> sendEmailWithAttachment() throws MessagingException {
        senderService.sendEmailWithAttachment("karthikmc007@gmail.com",
                "This is subject",
                "Hai Karthik Benki find the attachment below",
                "C:\\Users\\karth\\Downloads\\download.jpg");
        ResponseDTO responseDTO = new ResponseDTO("Sent Email Successfully", "toEmail");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    /**
     * @param userDTO
     * @return the status and user data
     * @Purpose to register the user into the data base
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUserInBookStore(@RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = userRegistrationService.registerUserInBookStore(userDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/verify/email/{otp}")
    public ResponseEntity<ResponseDTO> verifyEmailUsingOtp(@PathVariable Long otp) {
        ResponseDTO responseDTO = userRegistrationService.verifyEmailUsingOtp(otp);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        ResponseDTO responseDTO = userRegistrationService.loginUser(userLoginDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUserById(@PathVariable(value = "id") long id) {
        String email = userRegistrationService.deleteUserById(id);
        ResponseDTO responseDTO = new ResponseDTO("The data with " + id + " is deleted and the deleted is ", email);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> findUserById(@PathVariable(value = "id") long id) {
        UserData user = userRegistrationService.findUserById(id);
        ResponseDTO responseDTO = new ResponseDTO("The user found successfully", user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> findAllUsers(){
        List<UserData> userDataList = userRegistrationService.findAllUsers();
        ResponseDTO responseDTO = new ResponseDTO("The List of users found", userDataList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
