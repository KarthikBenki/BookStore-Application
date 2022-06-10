package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.entity.BookDetailsModel;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
