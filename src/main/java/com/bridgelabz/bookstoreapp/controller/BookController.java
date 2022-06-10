package com.bridgelabz.bookstoreapp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/welcome")
    public String welcomeBook(){
        return "Hello in Online Book Store DashBoard";
    }
}
