package com.store.amazing.controller;

import com.store.amazing.dto.request.product.CreateProduct;
import com.store.amazing.dto.response.product.ProductResponse;
import com.store.amazing.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<Long> addProduct(@Valid @RequestBody CreateProduct product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
