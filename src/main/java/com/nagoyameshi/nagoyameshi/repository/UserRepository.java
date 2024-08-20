package com.nagoyameshi.nagoyameshi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    public Optional<UserEntity> findByMailAddress(String mailAddress);
    public Optional<UserEntity> findByUserId(Integer userId);
    public Page<UserEntity> findByName(String name);
}