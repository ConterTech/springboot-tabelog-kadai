package com.nagoyameshi.nagoyameshi.service;

import org.springframework.stereotype.Service;

import com.nagoyameshi.nagoyameshi.entity.CategoryEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.form.CategoryEditForm;
import com.nagoyameshi.nagoyameshi.form.CategoryRegisterForm;
import com.nagoyameshi.nagoyameshi.repository.CategoryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // 登録
    @Transactional
    public void addCategory(CategoryRegisterForm categoryRegisterForm){
        CategoryEntity category = new CategoryEntity();

        category.setCategory(categoryRegisterForm.getCategory());

        categoryRepository.save(category);
    }

    // 編集
    @Transactional
    public void updateCategory(CategoryEditForm categoryEditForm){
        CategoryEntity category = categoryRepository.getReferenceById(categoryEditForm.getCategoryId());

        category.setCategory(categoryEditForm.getCategory());

        categoryRepository.save(category);
    }

    // カテゴリが登録済みかどうかチェックする
    public boolean isCategoryRegistered(String category) {
        CategoryEntity categories = categoryRepository.findByCategory(category);
        return categories != null;
    }
}
