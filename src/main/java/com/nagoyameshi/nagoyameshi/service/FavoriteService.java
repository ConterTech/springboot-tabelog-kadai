package com.nagoyameshi.nagoyameshi.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagoyameshi.nagoyameshi.entity.FavoriteEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.repository.FavoriteRepository;
import com.nagoyameshi.nagoyameshi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;

    // 追加
    @Transactional
    public void add(Integer userId, Integer storeId){
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりませんでした。"));
        FavoriteEntity favorite = new FavoriteEntity();

        favorite.setStoreId(storeId);
        favorite.setUserId(user);

        favoriteRepository.save(favorite);
    }
}
