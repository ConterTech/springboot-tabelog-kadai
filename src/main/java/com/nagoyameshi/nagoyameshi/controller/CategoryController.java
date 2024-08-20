package com.nagoyameshi.nagoyameshi.controller;

import com.nagoyameshi.nagoyameshi.repository.CategoryRepository;

public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
}
