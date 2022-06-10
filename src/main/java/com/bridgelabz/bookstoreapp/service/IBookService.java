package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.BookDetailsModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookService {

    BookDetailsModel addBook(BookDTO bookDTO);

    List<BookDetailsModel> getAllBooks();
}
