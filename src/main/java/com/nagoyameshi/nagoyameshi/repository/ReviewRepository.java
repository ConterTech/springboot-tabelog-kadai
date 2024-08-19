package com.nagoyameshi.nagoyameshi.repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.ReviewEntity;
import com.nagoyameshi.nagoyameshi.entity.Reviewpk;

public interface ReviewRepository extends JpaRepository<ReviewRepository, Reviewpk>{
    public Page<ReviewEntity> findByStoreId(Integer storeId, Pageable pageable);
    public Optional<ReviewEntity> findByStoreIdAndUserId(Integer storeId, Integer userId);
}
