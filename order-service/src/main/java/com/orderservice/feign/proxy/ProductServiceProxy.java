package com.orderservice.feign.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE", url = "http://localhost:8080/api/product")
public interface ProductServiceProxy {
    @GetMapping("/{sku}")
    public ResponseEntity<Boolean> validateSku(@PathVariable("sku") String sku);
}
