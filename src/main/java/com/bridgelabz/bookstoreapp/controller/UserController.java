package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstoreApi")
public class UserController {



    @Autowired
    private EmailSenderService senderService;

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


}
