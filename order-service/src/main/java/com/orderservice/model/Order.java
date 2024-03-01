package com.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "order_line_item")
    @OneToMany(cascade = CascadeType.ALL)
    private List<LineItem> orderLineItem;
    @Column(name = "total_item_count")
    private int totalItemCount;
}
