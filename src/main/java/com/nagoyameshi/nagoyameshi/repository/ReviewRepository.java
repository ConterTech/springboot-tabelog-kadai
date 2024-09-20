package com.nagoyameshi.nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nagoyameshi.nagoyameshi.entity.ReviewEntity;
import com.nagoyameshi.nagoyameshi.entity.Reviewpk;
import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Reviewpk>{
    public Page<ReviewEntity> findByStoreId(StoreEntity storeId, Pageable pageable);
    public ReviewEntity findByStoreIdAndUserId(StoreEntity storeId, UserEntity userId);
    @Transactional
    public void deleteByStoreIdAndUserId(StoreEntity storeId, UserEntity userId);
}
