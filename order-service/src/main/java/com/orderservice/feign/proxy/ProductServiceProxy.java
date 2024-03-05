package com.orderservice.feign.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="product-service")
public interface ProductServiceProxy {
    @GetMapping("/api/product/{sku}")
    public ResponseEntity<Boolean> validateSku(@PathVariable("sku") String sku);
}
