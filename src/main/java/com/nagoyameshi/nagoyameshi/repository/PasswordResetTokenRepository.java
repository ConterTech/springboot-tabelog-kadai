package com.nagoyameshi.nagoyameshi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagoyameshi.nagoyameshi.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    public PasswordResetToken findByToken(String token);
}
