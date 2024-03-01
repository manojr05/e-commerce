package com.productservice.service;

import com.productservice.dto.request.ProductRequestDTO;
import com.productservice.model.Product;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRequestDTO productRequestDTO);

    List<Product> getAllProduct(PageRequest pageRequest);
}
