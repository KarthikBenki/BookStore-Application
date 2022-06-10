package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BookDTO {
public String bookName;
public String authorName;
public String description;
public int rating;
public double bookPrice;
public double quantity;
public int publishingYear;

    public BookDTO(String bookName,
                   String authorName,
                   String description,
                   int rating,
                   double bookPrice,
                   double quantity,
                   int publishingYear) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
        this.rating = rating;
        this.bookPrice = bookPrice;
        this.quantity = quantity;
        this.publishingYear = publishingYear;
    }
}
