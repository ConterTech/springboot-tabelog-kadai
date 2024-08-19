package com.nagoyameshi.nagoyameshi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.ReservationEntity;
import com.nagoyameshi.nagoyameshi.entity.Reservationpk;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Reservationpk>{
    public Optional<ReservationEntity> findByStoreIdAndUserId(Integer storeId, Integer userId);
}
