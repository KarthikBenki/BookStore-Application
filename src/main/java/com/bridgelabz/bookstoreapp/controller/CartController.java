package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.BookDetailsModel;
import com.bridgelabz.bookstoreapp.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartApi")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping("/welcome")
    public String welcomeToCart() {
        return "Welcome to cart controller";
    }

    @PostMapping("/addBookToCart/{bookId}")
    public ResponseEntity<ResponseDTO> addBookToCart(@RequestHeader String token, @PathVariable Long bookId) {
        BookDetailsModel bookDetailsModel = cartService.addBookToCart(token, bookId);
        ResponseDTO responseDTO = new ResponseDTO("Book Added Successfully", bookDetailsModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
