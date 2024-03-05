package com.orderservice.controller;

import com.orderservice.dto.request.OrderRequestDTO;
import com.orderservice.model.Order;
import com.orderservice.service.OrderService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return new ResponseEntity<>(orderService.placeOrder(orderRequestDTO), HttpStatus.CREATED);
    }
}
