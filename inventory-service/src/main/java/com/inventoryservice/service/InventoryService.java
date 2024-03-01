package com.inventoryservice.service;

import com.inventoryservice.model.Inventory;

public interface InventoryService {
    Boolean isInStock(String sku, int quantity);

    Inventory addInventory(Inventory inventory);
}
