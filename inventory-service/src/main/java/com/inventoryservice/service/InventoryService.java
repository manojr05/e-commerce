package com.inventoryservice.service;

import com.inventoryservice.model.Inventory;

public interface InventoryService {
    Boolean isInStock(String sku);

    Inventory addInventory(Inventory inventory);
}
