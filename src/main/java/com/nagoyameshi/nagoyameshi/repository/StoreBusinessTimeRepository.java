package com.nagoyameshi.nagoyameshi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.StoreBusinessTimeEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreBusinessTimepk;

public interface StoreBusinessTimeRepository extends JpaRepository<StoreBusinessTimeEntity, StoreBusinessTimepk>{
    public Optional<StoreBusinessTimeEntity> findByStoreIdAndWeekday(Integer storeId, Integer weekday);
}
