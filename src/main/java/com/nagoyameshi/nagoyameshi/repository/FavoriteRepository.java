package com.nagoyameshi.nagoyameshi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.FavoriteEntity;
import com.nagoyameshi.nagoyameshi.entity.Favoritepk;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Favoritepk> {
    public Page<FavoriteEntity> findByStoreId(Integer StoreId, Pageable Pageable);
    public Optional<FavoriteEntity> findByStoreIdAndUserId(Integer storeId, Integer userId);
}
