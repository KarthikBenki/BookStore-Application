package com.bridgelabz.bookstoreapp.entity;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Component
@Entity
public class CartDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CartId;
    private Long bookId;
    private Long userId;
    private Double quantity;

    public void update(CartDTO cartDTO) {
        this.bookId = cartDTO.getBookId();
        this.userId = cartDTO.getUserId();
        this.quantity = cartDTO.getQuantity();
    }
}
