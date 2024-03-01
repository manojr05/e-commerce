package com.orderservice.service.impl;

import com.orderservice.dto.request.LineItemDTO;
import com.orderservice.dto.request.OrderRequestDTO;
import com.orderservice.exception.QuantityNotAvailableException;
import com.orderservice.feign.proxy.InventoryServiceProxy;
import com.orderservice.model.LineItem;
import com.orderservice.model.Order;
import com.orderservice.repository.OrderRepository;
import com.orderservice.service.OrderService;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final InventoryServiceProxy inventoryServiceProxy;

    @Override
    public Order placeOrder(OrderRequestDTO orderRequestDTO) {
        if(!validateOrder(orderRequestDTO))
            throw new QuantityNotAvailableException("Requested quantity not available");
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

    private boolean validateOrder(OrderRequestDTO orderRequestDTO){
        List<LineItemDTO> lineItemDTOs = orderRequestDTO.getLineItemDTO();
        AtomicBoolean flag= new AtomicBoolean(true);
        lineItemDTOs.forEach(lineItemDTO -> {
            if(!validateSku(lineItemDTO)){
                flag.set(false);
            }

        });
        return flag.get();
    }

    private boolean validateSku(LineItemDTO lineItemDTO){
        return Boolean.TRUE.equals(inventoryServiceProxy.isInStock(lineItemDTO.getSkuCode(), lineItemDTO.getQuantity()).getBody());
    }

}
