package com.nagoyameshi.nagoyameshi.repository;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.StoreSpecialBusinessTimeEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreSpecialBusinessTimepk;

public interface StoreSpecialBusinessTimeRepository extends JpaRepository<StoreSpecialBusinessTimeEntity, StoreSpecialBusinessTimepk>{
    public Optional<StoreSpecialBusinessTimeEntity> findByStoreIdAndSpecilaBusinessDay(Integer storeId, LocalTime specialBusinessDay);
}
