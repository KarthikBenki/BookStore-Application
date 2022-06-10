package com.bridgelabz.bookstoreapp.controller;


import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.BookDetailsModel;
import com.bridgelabz.bookstoreapp.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping("/welcome")
    public String welcomeBook() {
        return "Hello in Online Book Store DashBoard";
    }

    @PostMapping("/addBook")
    public ResponseEntity<ResponseDTO> addBook(@RequestBody BookDTO bookDTO) {
        System.out.println(bookDTO);
        BookDetailsModel bookDetailsModel = bookService.addBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book Added Successfully", bookDetailsModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);


    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<ResponseDTO> getAllBooks(){
        List<BookDetailsModel> bookDetailsModels = bookService.getAllBooks();
        ResponseDTO responseDTO = new ResponseDTO("Got All Books List",bookDetailsModels);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }
}
