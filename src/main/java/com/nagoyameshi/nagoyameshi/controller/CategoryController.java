package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.stereotype.Controller;

import com.nagoyameshi.nagoyameshi.repository.CategoryRepository;

@Controller
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
}
