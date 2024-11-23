package com.nagoyameshi.nagoyameshi.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.form.UserEditForm;
import com.nagoyameshi.nagoyameshi.repository.UserRepository;
import com.nagoyameshi.nagoyameshi.security.UserDetailsImpl;
import com.nagoyameshi.nagoyameshi.service.StripeService;
import com.nagoyameshi.nagoyameshi.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final StripeService stripeService;

    // ユーザ情報表示
    @GetMapping
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, HttpServletRequest httpServletRequest,
            Model model) {
        UserEntity user = userRepository.getReferenceById(userDetailsImpl.getUser().getUserId());
        Integer userId = user.getUserId();
        String sessionId = stripeService.createStripeSession(userId, httpServletRequest);

        // ユーザ情報の更新（ログアウト不要）
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        UserDetails userDetails = new UserDetailsImpl(user, authorities);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                        userDetails.getAuthorities()));

        model.addAttribute("user", user);
        model.addAttribute("sessionId", sessionId);

        return "user/index";
    }

    // 編集画面表示
    @GetMapping("/edit")
    public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
        UserEntity user = userRepository.getReferenceById(userDetailsImpl.getUser().getUserId());
        UserEditForm userEditForm = new UserEditForm(user.getUserId(), user.getName(), user.getPhoneNumber(),
                user.getPostCode(), user.getAddress(), user.getEmail(), user.getAge(), user.getGender());

        model.addAttribute("userEditForm", userEditForm);
        model.addAttribute("user", user);

        return "user/edit";
    }

    // 編集
    @PostMapping("/update")
    public String update(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @ModelAttribute @Validated UserEditForm userEditForm, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) {
        if (userService.isEmailChanged(userEditForm) && userService.isEmailRegistered(userEditForm.getEmail())) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "既に登録済みのメールアドレスです。");
            bindingResult.addError(fieldError);
        }

        if (bindingResult.hasErrors()) {
            UserEntity user = userRepository.getReferenceById(userDetailsImpl.getUser().getUserId());
            model.addAttribute("user",user);
            return "user/edit";
        }

        userService.update(userEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "会員情報を編集しました。");

        return "redirect:/user";
    }
}
