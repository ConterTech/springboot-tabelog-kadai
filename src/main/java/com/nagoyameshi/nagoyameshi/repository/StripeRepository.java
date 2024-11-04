package com.nagoyameshi.nagoyameshi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.StripeEntity;

public interface StripeRepository extends JpaRepository<StripeEntity, Integer>{
    
}
