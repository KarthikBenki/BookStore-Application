package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.entity.BookDetailsModel;

public interface ICartService {
    BookDetailsModel addBookToCart(String token, Long bookId);
}
