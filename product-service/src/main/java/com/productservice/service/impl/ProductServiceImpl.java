package com.productservice.service.impl;

import com.productservice.dto.request.ProductRequestDTO;
import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;
import com.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRequestDTO productRequestDTO) {
        return productRepository.save(
                Product.builder()
                        .name(productRequestDTO.getName())
                        .description(productRequestDTO.getDescription())
                        .price(productRequestDTO.getPrice())
                        .build()
        );
    }

    @Override
    public List<Product> getAllProduct(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).toList();
    }


}
