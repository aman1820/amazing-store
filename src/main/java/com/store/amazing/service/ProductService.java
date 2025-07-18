package com.store.amazing.service;

import com.store.amazing.dto.request.product.CreateProduct;
import com.store.amazing.dto.response.product.ProductResponse;
import com.store.amazing.entity.Category;
import com.store.amazing.entity.Product;
import com.store.amazing.repository.CategoryRepository;
import com.store.amazing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Long addProduct(CreateProduct request) {
        Category category = categoryRepository
                .findById(request.categoryId())
                .orElseThrow(() -> new RuntimeException(String.format("Category with category id %d not found", request.categoryId())));

        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .stock(request.stock())
                .category(category)
                .build();
        return productRepository.save(product).getId();
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getCategory().getName()))
                .toList();
    }
}
