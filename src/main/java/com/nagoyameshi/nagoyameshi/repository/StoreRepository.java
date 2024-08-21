package com.nagoyameshi.nagoyameshi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer>{
    public Page<StoreEntity> findByStoreId(Integer storeId, Pageable pageable);
    public Optional<StoreEntity> findByStoreId(Integer storeId);
}
