package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.entity.OrderDetailsModel;
import com.bridgelabz.bookstoreapp.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/orderApi")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseDTO> placeOrder(@Valid @RequestBody OrderDTO orderDTO) {
        OrderDetailsModel orderDetailsModel = orderService.placeOrder(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("placed order successfully",orderDetailsModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);


    }
}
