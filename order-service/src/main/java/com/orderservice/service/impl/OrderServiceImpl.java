package com.orderservice.service.impl;

import com.orderservice.dto.request.LineItemDTO;
import com.orderservice.dto.request.OrderRequestDTO;
import com.orderservice.model.LineItem;
import com.orderservice.model.Order;
import com.orderservice.repository.OrderRepository;
import com.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order placeOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        List<LineItem> lineItems = orderRequestDTO.getLineItemDTO().stream().map(this::mapLineItemDTOToLineItem).toList();
        order.setOrderLineItem(lineItems);
        order.setTotalItemCount(extractItemCount(lineItems));
        return orderRepository.save(order);
    }

    private int extractItemCount(List<LineItem> lineItems) {
        AtomicInteger count = new AtomicInteger();
        lineItems.forEach(i-> count.set(count.get() + i.getQuantity()));
        return count.get();
    }

    private LineItem mapLineItemDTOToLineItem(LineItemDTO lineItemDTO){
        return LineItem.builder()
                .id(UUID.randomUUID().toString())
                .price(lineItemDTO.getPrice())
                .skuCode(lineItemDTO.getSkuCode())
                .quantity(lineItemDTO.getQuantity())
                .build();
    }

}
