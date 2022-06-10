package com.bridgelabz.bookstoreapp.entity;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Component
@Entity
@Table
public class BookDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    private String bookName;
    private String authorName;
    private String description;
    private double bookPrice;
    private double quantity;
    private int rating;
    private int publishingYear;
    private LocalDateTime createdAt = LocalDateTime.now();


    public BookDetailsModel(Long bookId, String bookName, String authorName, String description, double bookPrice, double quantity, int rating, int publishingYear) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        this.bookPrice = bookPrice;
        this.quantity = quantity;
        this.rating = rating;
        this.publishingYear = publishingYear;

    }

    public BookDetailsModel() {
    }

    public BookDetailsModel(BookDTO bookDTO){
        this.bookName = bookDTO.getBookName();
        this.authorName = bookDTO.getAuthorName();
        this.description = bookDTO.getDescription();
        this.bookPrice = bookDTO.getBookPrice();
        this.quantity = bookDTO.getQuantity();
        this.rating = bookDTO.getRating();
        this.publishingYear = bookDTO.getPublishingYear();
    }


}