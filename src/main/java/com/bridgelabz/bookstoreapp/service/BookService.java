package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.BookDetailsModel;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
    BookDetailsModel bookDetailsModel;

    @Override
    public BookDetailsModel addBook(BookDTO bookDTO) {
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
