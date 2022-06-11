package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.BookDetailsModel;
import com.bridgelabz.bookstoreapp.entity.UserData;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.exception.UserException;
import com.bridgelabz.bookstoreapp.repository.IBookRepository;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import com.bridgelabz.bookstoreapp.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private BookDetailsModel bookDetailsModel;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Override
    public BookDetailsModel addBook(BookDTO bookDTO, String token) {
        Long id  = Long.valueOf(tokenGenerator.decodeJWT(token));
        UserData userData = userRegistrationRepository.findById(id)
                .orElseThrow(()->new UserException("User not found",UserException.ExceptionType.USER_NOT_FOUND));
        System.out.println(userData.getEmail());

        Optional<BookDetailsModel> searchByName = bookRepository.findByBookName(bookDTO.getBookName());
        if (searchByName.isPresent()) {
            throw new BookStoreException(BookStoreException.ExceptionTypes.BOOK_AlREADY_PRESENT);
        }
        bookDetailsModel = new BookDetailsModel(bookDTO);
        return bookRepository.save(bookDetailsModel);
    }

    @Override
    public List<BookDetailsModel> getAllBooks() {
        if (bookRepository.findAll().size() == 0) {
            throw new BookStoreException(BookStoreException.ExceptionTypes.NO_BOOKS_FOUND);
        }
        return bookRepository.findAll();
    }

    @Override
    public int getCountOfBooks() {
        return bookRepository.findAll().size();
    }

    @Override
    public List<BookDetailsModel> getBooksWithIncreasingOrderOfTheirPrice() {
        List<BookDetailsModel> bookDetails = bookRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(bookDetailsModel->bookDetailsModel.getBookPrice()))
                .collect(Collectors.toList());
        return bookDetails;
    }

    @Override
    public List<BookDetailsModel> getBooksWithDecreasingOrderOfTheirPrice() {
        List<BookDetailsModel> bookDetails = bookRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(bookDetailsModel -> bookDetailsModel.getBookPrice()))
                .collect(Collectors.toList());
        Collections.reverse(bookDetails);
        return bookDetails;
    }

    @Override
    public List<BookDetailsModel> getBooksWithPublishingYear() {
        List<BookDetailsModel> bookDetails = bookRepository.findAll()
                .stream().sorted(Comparator.comparing(bookDetailsModel -> bookDetailsModel.getPublishingYear()))
                .collect(Collectors.toList());
        Collections.reverse(bookDetails);
        return bookDetails;
    }

    @Override
    public List<BookDetailsModel> getBooksByNewLaunch() {
        List<BookDetailsModel> bookDetails = bookRepository.findAll()
                .stream().sorted(Comparator.comparing(bookDetailsModel -> bookDetailsModel.getCreatedAt()))
                .collect(Collectors.toList());
        return bookDetails;
    }

    @Override
    public BookDetailsModel getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(()->new BookStoreException(BookStoreException.ExceptionTypes.BOOK_NOT_FOUND));
    }


}
