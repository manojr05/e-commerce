package com.inventoryservice.controller;

import com.inventoryservice.model.Inventory;
import com.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<Boolean> isInStock(@RequestParam String sku, @RequestParam int quantity){
        return new ResponseEntity<>(inventoryService.isInStock(sku, quantity), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory){
        return new ResponseEntity<Inventory>(inventoryService.addInventory(inventory), HttpStatus.CREATED);
    }

}
