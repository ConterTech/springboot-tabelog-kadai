package com.nagoyameshi.nagoyameshi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nagoyameshi.nagoyameshi.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    public CategoryEntity findByCategory(String category);
}
