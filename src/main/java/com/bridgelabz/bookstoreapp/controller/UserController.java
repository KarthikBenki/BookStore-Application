package com.bridgelabz.bookstoreapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstoreApi")
public class UserController {

    /**
     * @Purpose Testing the Api
     * @return A Welcome message to test the api
     */
    @GetMapping({"/welcome","/",""})
    public String welcomeApi() {
        return "Welcome To BookStore App";
    }


}
