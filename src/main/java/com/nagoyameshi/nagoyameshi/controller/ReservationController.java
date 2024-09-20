package com.nagoyameshi.nagoyameshi.controller;

import java.time.LocalDateTime;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagoyameshi.nagoyameshi.entity.ReservationEntity;
import com.nagoyameshi.nagoyameshi.entity.StoreEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.form.ReservationInputForm;
import com.nagoyameshi.nagoyameshi.form.ReservationRegisterForm;
import com.nagoyameshi.nagoyameshi.repository.ReservationRepository;
import com.nagoyameshi.nagoyameshi.repository.StoreRepository;
import com.nagoyameshi.nagoyameshi.security.UserDetailsImpl;
import com.nagoyameshi.nagoyameshi.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Controller
@SessionAttributes("reservationRegisterForm")
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

        model.addAttribute("reservationPage", reservationPage);

        return "reservation/index";
    }

    // 予約削除
    @PostMapping("/reservation/{reservationId}/delete")
    public String delete(@PathVariable(name = "reservationId")Integer reservationId, RedirectAttributes redirectAttributes) {
        reservationRepository.deleteById(reservationId);

        redirectAttributes.addFlashAttribute("successMessage", "予約をキャンセルしました。");

        return "redirect:/reservation";
    }

    // 予約フォーム
    @GetMapping("/store/{storeId}/reservation/input")
    public String input(@PathVariable(name = "storeId") Integer storeId,
            @ModelAttribute @Validated ReservationInputForm reservationInputForm,
            BindingResult bindingResult,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            RedirectAttributes redirectAttributes,
            Model model) {
        StoreEntity store = storeRepository.getReferenceById(storeId);
        UserEntity user = userDetailsImpl.getUser();

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("store", store);
            model.addAttribute("errorMessage", "予約内容に不備があります。");
            return "store/show";
        }

        redirectAttributes.addFlashAttribute("reservationInputForm", reservationInputForm);

        return "redirect:/store/{storeId}/reservation/confirm";
    }

    // 予約確定前画面表示
    @GetMapping("/store/{storeId}/reservation/confirm")
    public String confirm(@PathVariable(name = "storeId") Integer storeId,
            @ModelAttribute ReservationInputForm reservationInputForm,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            Model model) {

        StoreEntity store = storeRepository.getReferenceById(storeId);
        UserEntity user = userDetailsImpl.getUser();

        LocalDateTime checkinTime = reservationInputForm.getCheckinTime();

        ReservationRegisterForm reservationRegisterForm = new ReservationRegisterForm(store.getStoreId(),
                user.getUserId(), checkinTime,
                reservationInputForm.getNumberOfPeople(),
                reservationInputForm.getRemarks());

        model.addAttribute("store", store);
        model.addAttribute("reservationRegisterForm", reservationRegisterForm);

        return "reservation/confirm";
    }

    // 予約
    @PostMapping("/store/{storeId}/reservation/create")
    public String create(@ModelAttribute ReservationRegisterForm reservationRegisterForm) {
        reservationService.create(reservationRegisterForm);

        return "redirect:/reservation?reserved";
    }
}
