package com.store.amazing.controller;

import com.store.amazing.dto.request.category.CreateCategory;
import com.store.amazing.dto.response.category.CategoryResponse;
import com.store.amazing.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
@Validated
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Long> createCategory(@Valid @RequestBody CreateCategory request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(request));
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getCategory() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

}
