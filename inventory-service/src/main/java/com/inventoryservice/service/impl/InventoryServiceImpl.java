package com.inventoryservice.service.impl;

import com.inventoryservice.model.Inventory;
import com.inventoryservice.repository.InventoryRepository;
import com.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public Boolean isInStock(String sku, int quantity) {
        Optional<Inventory> product = inventoryRepository.findById(sku);
        return product.filter(inventory -> inventory.getQuantity() >= quantity).isPresent();
    }

    @Override
    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}
