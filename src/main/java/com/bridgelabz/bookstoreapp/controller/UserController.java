package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.service.EmailSenderService;
import com.bridgelabz.bookstoreapp.service.IUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/bookstoreApi")
public class UserController {



    @Autowired
    private EmailSenderService senderService;

    @Autowired
    private IUserRegistrationService userRegistrationService;

    /**
     * @Purpose Testing the Api
     * @return A Welcome message to test the api
     */
    @GetMapping({"/welcome","/",""})
    public String welcomeApi() {
        return "Welcome To BookStore App";
    }


    /**
     * @Purpose To check if the test mail is sending or not
     * @return status of email
     */
    @GetMapping("/email/{toEmail}/{subject}/{body}")
    public ResponseEntity<ResponseDTO> sendTestEmail(@PathVariable String toEmail,
                                                     @PathVariable String subject,
                                                     @PathVariable String body){
        senderService.sendEmail(toEmail,
                subject,
                body);
        ResponseDTO responseDTO = new ResponseDTO("Sent Email Successfully",toEmail);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    /**
     *
     * @return status of email
     * @throws MessagingException
     */
    @GetMapping("/emailAttached")
    public ResponseEntity<ResponseDTO> sendEmailWithAttachment() throws MessagingException {
        senderService.sendEmailWithAttachment("karthikmc007@gmail.com",
                "This is subject",
                "Hai Karthik Benki find the attachment below",
                "C:\\Users\\karth\\Downloads\\download.jpg");
        ResponseDTO responseDTO = new ResponseDTO("Sent Email Successfully","toEmail");
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }


    /**
     * @Purpose to register the user into the data base
     * @param userDTO
     * @return the status and user data
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUserInBookStore(@RequestBody UserDTO userDTO){
        ResponseDTO responseDTO = userRegistrationService.registerUserInBookStore(userDTO);
        return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
    }

    @GetMapping("/verify/email/{otp}")
    public ResponseEntity<ResponseDTO> verifyEmailUsingOtp(@PathVariable Long otp){
        ResponseDTO responseDTO = userRegistrationService.verifyEmailUsingOtp(otp);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody UserLoginDTO userLoginDTO){
        ResponseDTO responseDTO = userRegistrationService.loginUser(userLoginDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


}
