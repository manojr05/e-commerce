package com.orderservice.feign.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="inventory-service")
public interface InventoryServiceProxy {

    @GetMapping("/api/inventory")
    public ResponseEntity<Boolean> isInStock(@RequestParam String sku, @RequestParam int quantity);

}
