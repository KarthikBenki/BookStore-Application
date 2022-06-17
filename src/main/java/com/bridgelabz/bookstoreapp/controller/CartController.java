package com.bridgelabz.bookstoreapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartApi")
public class CartController {

    @GetMapping("/welcome")
    public String welcomeToCart(){
        return "Welcome to cart controller";
    }
}
