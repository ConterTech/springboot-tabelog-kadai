package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagoyameshi.nagoyameshi.entity.ReservationEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.form.ReservationInputForm;
import com.nagoyameshi.nagoyameshi.form.ReservationRegisterForm;
import com.nagoyameshi.nagoyameshi.form.StoreEditForm;
import com.nagoyameshi.nagoyameshi.repository.ReservationRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;
import com.nagoyameshi.nagoyameshi.security.UserDetailsImpl;
import com.nagoyameshi.nagoyameshi.service.ReservationService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;
    private final ReservationService reservationService;

    // 予約一覧
    @GetMapping("/reservation")
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @PageableDefault(page = 0, size = 10, sort = "storeId", direction = Direction.ASC) Pageable pageable,
            Model model) {
        UserEntity user = userDetailsImpl.getUser();
        Page<ReservationEntity> reservationPage = reservationRepository.findByUserId(user, pageable);

        model.addAttribute(reservationPage);
        return "index";
    }

    // 予約確定前画面表示
    @GetMapping("/store/{storeId}/reservation/confirm")
    public String confirm(@PathVariable(name = "storeId") Integer storeId,
            @ModelAttribute ReservationInputForm reservationInputForm,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, HttpServletRequest httpServletRequest,
            Model model) {

        StoreEntity store = storeRepository.getReferenceById(storeId);
        UserEntity user = userDetailsImpl.getUser();

        ReservationRegisterForm reservationRegisterForm = new ReservationRegisterForm(store.getStoreId(),
                user.getUserId(), reservationInputForm.getCheckinTime().toString(),
                reservationInputForm.getNumberOfPeople(),
                reservationInputForm.getRemarks());

        model.addAttribute(reservationRegisterForm);

        return "reservation/confirm";
    }

    // 予約確定前確認
    @PostMapping("/store/{storeId}/reservation/create")
    public String create(@PathVariable(name = "storeId") Integer storeId,
            @ModelAttribute ReservationRegisterForm reservationRegisterForm, Model model) {
        reservationService.create(reservationRegisterForm);

        return "redirect:/reservation?reserved";
    }
}
