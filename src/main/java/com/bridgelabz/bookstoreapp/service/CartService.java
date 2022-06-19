package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.entity.BookDetailsModel;
import com.bridgelabz.bookstoreapp.entity.CartDetailsModel;
import com.bridgelabz.bookstoreapp.entity.UserData;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.exception.CartException;
import com.bridgelabz.bookstoreapp.repository.IBookRepository;
import com.bridgelabz.bookstoreapp.repository.ICartRepository;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Signature;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    private BookDetailsModel bookDetailsModel;

    @Autowired
    private UserData userData;

    @Autowired
    private IBookService bookService;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private CartDetailsModel cartDetailsModel;

    @Autowired
    private ICartRepository cartRepository;

    @Override
    public BookDetailsModel addBookToCart(String token, Long bookId) {
        Optional<CartDetailsModel> cartModel = cartRepository.findByBookDetailsById(bookId);
        if(cartModel.isPresent()){
            throw new CartException(CartException.ExceptionTypes.BOOK_ALREADY_PRESENT);
        }
        bookDetailsModel = bookService.getBookById(bookId);
        userData = userRegistrationService.findUserById(bookDetailsModel.getUserId());
        cartDetailsModel.setBookDetailsModel(bookDetailsModel);
        cartDetailsModel.setUserData(userData);
        cartDetailsModel.setQuantity(bookDetailsModel.getQuantity());
                cartRepository.save(cartDetailsModel);
        return bookDetailsModel;
    }

    @Override
    public List<CartDetailsModel> getAll() {
        List<CartDetailsModel> cartDetailsModelList = cartRepository.findAll();
        return cartDetailsModelList;
    }
}
