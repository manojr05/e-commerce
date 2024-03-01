package com.orderservice.service;

import com.orderservice.dto.request.OrderRequestDTO;
import com.orderservice.model.Order;

public interface OrderService {
    Order placeOrder(OrderRequestDTO orderRequestDTO);
}
