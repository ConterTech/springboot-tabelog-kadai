package com.nagoyameshi.nagoyameshi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.MenuPhotoEntity;
import com.nagoyameshi.nagoyameshi.entity.MenuPhotopk;

public interface MenuPhotoRepository extends JpaRepository<MenuPhotoEntity, MenuPhotopk>{
    public Optional<MenuPhotoEntity> findByStoreIdAndMenuId(Integer storeId, Integer menuId);
}
