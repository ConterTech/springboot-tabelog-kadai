package com.nagoyameshi.nagoyameshi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagoyameshi.nagoyameshi.entity.FavoriteEntity;
import com.nagoyameshi.nagoyameshi.entity.ReviewEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreBusinessTimeEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.form.ReservationInputForm;
import com.nagoyameshi.nagoyameshi.repository.FavoriteRepository;
import com.nagoyameshi.nagoyameshi.repository.ReviewRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreBusinessTimeRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;
import com.nagoyameshi.nagoyameshi.security.UserDetailsImpl;
import com.nagoyameshi.nagoyameshi.service.FavoriteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreRepository storeRepository;
    private final StoreBusinessTimeRepository storeBusinessTimeRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteRepository favoriteRepository;
    private final FavoriteService favoriteService;

    // 店舗詳細ページ
    @GetMapping("/{storeId}")
    public String show(@PathVariable(name = "storeId") Integer storeId,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @PageableDefault(page = 0, size = 6, sort = "storeId", direction = Direction.ASC) Pageable pageable,
            Model model) {
        StoreEntity store = storeRepository.getReferenceById(storeId);
        List<StoreBusinessTimeEntity> storeBusinessTime = storeBusinessTimeRepository.findByStoreId(store);
        Page<ReviewEntity> review = reviewRepository.findByStoreId(store, pageable);

        model.addAttribute("store", store);
        model.addAttribute("storeBusinessTime", storeBusinessTime);
        model.addAttribute("review", review);
        model.addAttribute("reservationInputForm", new ReservationInputForm());

        if (userDetailsImpl != null) {
            UserEntity user = userDetailsImpl.getUser();
            Optional<FavoriteEntity> favorite = favoriteRepository.findByStoreIdAndUserId(store, user);
            model.addAttribute("favorite", favorite);
            model.addAttribute("userId", userDetailsImpl.getUser().getUserId());

            boolean hasUserReviewed = review.stream()
                    .anyMatch(reviews -> ((UserDetailsImpl) review).getUser().getUserId()
                            .equals(userDetailsImpl.getUser().getUserId()));
            //boolean hasFavorite = favorite.stream()
              //      .anyMatch(favorites -> favorite.getUserEntity().getUserId().equals(userDetailsImpl.getUser().getUserId()));

            model.addAttribute("hasUserReviewed", hasUserReviewed);
            //model.addAttribute("hasFavorite", hasFavorite);
        }

        return "store/show";
    }

    // お気に入り追加
    @PostMapping("/{storeId}/addFavorite")
    public String addFavorite(@PathVariable(name = "storeId") Integer storeId,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        Integer userId = userDetailsImpl.getUser().getUserId();

        favoriteService.add(userId, storeId);

        return "redirect:/store/{storeId}";
    }

    // お気に入り削除
    @PostMapping("/{storeId}/deleteFavorite")
    public String deleteFavorite(@PathVariable(name = "storeId") Integer storeId,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        UserEntity user = userDetailsImpl.getUser();
        StoreEntity store = storeRepository.getReferenceById(storeId);

        favoriteRepository.deleteByUserIdAndStoreId(user, store);

        return "redirect:/store/{storeId}";
    }

    @GetMapping("/{storeId}/reservation/input")
    public String input(@PathVariable(name = "storeId") Integer storeId,
            @ModelAttribute @Validated ReservationInputForm reservationInputForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {
        StoreEntity store = storeRepository.getReferenceById(storeId);

        if (bindingResult.hasErrors()) {
            model.addAttribute("store", store);
            model.addAttribute("errorMessage", "予約内容に不備があります。");
            return "store/show";
        }

        redirectAttributes.addFlashAttribute("reservationInputForm", reservationInputForm);

        return "redirect:/store/{storeId}/reservation/confirm";
    }
}
