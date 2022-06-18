package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.entity.BookDetailsModel;
import com.bridgelabz.bookstoreapp.entity.CartDetailsModel;
import com.bridgelabz.bookstoreapp.repository.IBookRepository;
import com.bridgelabz.bookstoreapp.repository.ICartRepository;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Signature;

@Service
public class CartService implements ICartService {

    @Autowired
    BookDetailsModel bookDetailsModel;
    @Autowired
    IBookRepository bookRepository;

    @Autowired
    IBookService bookService;

    @Autowired
    private CartDTO cartDTO;

    @Autowired
    private CartDetailsModel cartDetailsModel;

    @Autowired
    private ICartRepository cartRepository;

    @Override
    public BookDetailsModel addBookToCart(String token, Long bookId) {
        bookDetailsModel = bookService.getBookById(bookId);
        cartDTO.setBookId(bookDetailsModel.getBookId());
        cartDTO.setUserId(bookDetailsModel.getUserId());
        cartDTO.setQuantity(bookDetailsModel.getQuantity());
        cartDetailsModel.update(cartDTO);
        cartRepository.save(cartDetailsModel);
        return bookDetailsModel;
    }
}
