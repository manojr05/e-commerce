package com.productservice.controller;

import com.productservice.dto.request.ProductRequestDTO;
import com.productservice.model.Product;
import com.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        log.debug("Received request to create product: {}", productRequestDTO);
        return new ResponseEntity<>(productService.createProduct(productRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size){
        log.debug("Received request to get all products with page: {} and size: {}", page, size);
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok()
                .body(productService.getAllProduct(pageRequest));
    }

    @GetMapping("/{sku}")
    public  ResponseEntity<Boolean> validateSku(@PathVariable("sku") String sku){
        log.debug("Received request to validate sku: {}", sku);
        return ResponseEntity.ok()
                .body(productService.validateSku(sku));
    }
}
