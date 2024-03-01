package com.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity(name = "line_item")
@NoArgsConstructor
@AllArgsConstructor
public class LineItem {
    @Id
    private String id;
    private String skuCode;
    private double price;
    private int quantity;
}
