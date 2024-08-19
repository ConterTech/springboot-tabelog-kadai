package com.nagoyameshi.nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.MenuEntity;
import com.nagoyameshi.nagoyameshi.entity.Menupk;

public interface MenuRepository extends JpaRepository<MenuEntity, Menupk>{
    public Page<MenuEntity> findByStoreId(Integer storeId, Pageable pageable);
}
