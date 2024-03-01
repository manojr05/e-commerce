package com.inventoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Inventory {
    @Id
    private String sku;
    private int quantity;
}
