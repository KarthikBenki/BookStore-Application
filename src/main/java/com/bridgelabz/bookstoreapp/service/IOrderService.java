package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.entity.OrderDetailsModel;

public interface IOrderService {
    OrderDetailsModel placeOrder(OrderDTO orderDTO);
}
