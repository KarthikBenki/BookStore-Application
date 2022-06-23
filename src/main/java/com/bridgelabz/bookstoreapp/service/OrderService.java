package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.entity.BookDetailsModel;
import com.bridgelabz.bookstoreapp.entity.OrderDetailsModel;
import com.bridgelabz.bookstoreapp.entity.UserData;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.exception.OrderException;
import com.bridgelabz.bookstoreapp.repository.IBookRepository;
import com.bridgelabz.bookstoreapp.repository.IOrderRepository;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class OrderService implements IOrderService{
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private UserRegistrationRepository userRepository;
    @Autowired
    private IOrderRepository orderRepository;



    @Override
    public OrderDetailsModel placeOrder(OrderDTO orderDTO) {
        Optional<BookDetailsModel> book = bookRepository.findById(orderDTO.getBookId());
        Optional<UserData> user = userRepository.findById(orderDTO.getUserId());
        if(book.isPresent()&&user.isPresent()) {
            if(orderDTO.getQuantity()<=book.get().getQuantity()){
                OrderDetailsModel order =
                        new OrderDetailsModel(
                                orderDTO.getPrice(),
                                orderDTO.getQuantity(),
                                orderDTO.getAddress(),
                                user.get(),
                                book.get(),
                                orderDTO.getCancel()
                        );
                orderRepository.save(order);
                log.info("order record inserted successfully");
                return order;
            }else {
                throw new OrderException(OrderException.ExceptionTypes.BOOK_QUANTITY_EXCEEDED);
            }
        }
        throw new  BookStoreException(BookStoreException.ExceptionTypes.BOOK_OR_USER_DOES_NOT_EXIST);
    }
}
