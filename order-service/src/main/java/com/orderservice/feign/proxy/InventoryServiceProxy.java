package com.orderservice.feign.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-SERVICE", url = "http://localhost:8082/api/inventory")
public interface InventoryServiceProxy {

    @GetMapping
    public ResponseEntity<Boolean> isInStock(@RequestParam String sku, @RequestParam int quantity);

}
