package com.store.amazing.service;

import com.store.amazing.dto.request.category.CreateCategory;
import com.store.amazing.dto.response.category.CategoryResponse;
import com.store.amazing.entity.Category;
import com.store.amazing.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Long createCategory(CreateCategory request) {
        if(categoryRepository.findByNameIgnoreCase(request.name()).isPresent()) {
            throw new RuntimeException("Category already exist");
        }

        Category category = Category.builder().name(request.name()).build();
        return categoryRepository.save(category).getId();
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryResponse(category.getId(), category.getName()))
                .toList();
    }
}
