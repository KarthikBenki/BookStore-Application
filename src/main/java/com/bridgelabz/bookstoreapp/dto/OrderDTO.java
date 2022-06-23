package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
//Data transfer object Order model
@Data
public class OrderDTO {
    private Double quantity;
    @NotEmpty(message = "please provide address")
    private String address;
    private Long userId;
    private Long bookId;
    private Boolean cancel;
    private Double price;
}
