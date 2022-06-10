package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.BookDetailsModel;
import org.springframework.stereotype.Service;

@Service
public interface IBookService {

    BookDetailsModel addBook(BookDTO bookDTO);
}
