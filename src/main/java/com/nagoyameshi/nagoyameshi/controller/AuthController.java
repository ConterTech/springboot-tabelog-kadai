package com.nagoyameshi.nagoyameshi.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagoyameshi.nagoyameshi.entity.StripeEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.entity.VerificationToken;
import com.nagoyameshi.nagoyameshi.event.PasswordResetEventPublisher;
import com.nagoyameshi.nagoyameshi.event.SignupEventPublisher;
import com.nagoyameshi.nagoyameshi.form.PasswordEmailForm;
import com.nagoyameshi.nagoyameshi.form.SignupForm;
import com.nagoyameshi.nagoyameshi.repository.StripeRepository;
import com.nagoyameshi.nagoyameshi.repository.UserRepository;
import com.nagoyameshi.nagoyameshi.security.UserDetailsImpl;
import com.nagoyameshi.nagoyameshi.service.StripeService;
import com.nagoyameshi.nagoyameshi.service.UserService;
import com.nagoyameshi.nagoyameshi.service.VerificationTokenService;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Subscription;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final VerificationTokenService verificationTokenService;
    private final StripeService stripeService;
    private final SignupEventPublisher signupEventPublisher;
    private final PasswordResetEventPublisher passwordResetEventPublisher;
    private final UserRepository userRepository;
    private final StripeRepository stripeRepository;

    // ログイン
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // 新規会員登録画面表示
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "auth/signup";
    }

    // パスワード変更メールアドレス入力画面
    @GetMapping("/password/reset")
    public String password(Model model) {
        model.addAttribute("passwordEmailForm", new PasswordEmailForm());
        return "auth/password";
    }

    // パスワード変更メール送信
    @PostMapping("/password/reset/post")
    public String reset(@ModelAttribute @Validated PasswordEmailForm passwordEmailForm, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        // メールアドレスが未登録なら、BindingResultオブジェクトにエラー内容を追加する
        if (!userService.isEmailRegistered(passwordEmailForm.getEmail())) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "未登録のメールアドレスです。");
            bindingResult.addError(fieldError);
        }

        if (bindingResult.hasErrors()) {
            return "auth/password";
        }

        UserEntity user = userRepository.findByEmail(passwordEmailForm.getEmail());
        String requestUrl = new String(httpServletRequest.getRequestURL());
        passwordResetEventPublisher.publishResetEvent(user, requestUrl);
        redirectAttributes.addFlashAttribute("successMessage",
                "ご入力いただいたメールアドレスに認証メールを送信しました。メールに記載されているリンクをクリックし、会員登録を完了してください。");
        return "redirect:/";
    }

    // 新規会員登録
    @PostMapping("/signup")
    public String signup(@ModelAttribute @Validated SignupForm signupForm, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
        // メールアドレスが登録済みであれば、BindingResultオブジェクトにエラー内容を追加する
        if (userService.isEmailRegistered(signupForm.getEmail())) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
            bindingResult.addError(fieldError);
        }

        // パスワードとパスワード（確認用）の入力値が一致しなければ、BindingResultオブジェクトにエラー内容を追加する
        if (!userService.isSamePassword(signupForm.getPassword(), signupForm.getPasswordConfirmation())) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "password", "パスワードが一致しません。");
            bindingResult.addError(fieldError);
        }

        if (bindingResult.hasErrors()) {
            return "auth/signup";
        }

        UserEntity createdUser = userService.create(signupForm);
        String requestUrl = new String(httpServletRequest.getRequestURL());
        signupEventPublisher.publishSignupEvent(createdUser, requestUrl);
        redirectAttributes.addFlashAttribute("successMessage",
                "ご入力いただいたメールアドレスに認証メールを送信しました。メールに記載されているリンクをクリックし、会員登録を完了してください。");

        return "redirect:/";
    }

    // ユーザ登録認証後画面
    @GetMapping("/signup/verify")
    public String verify(@RequestParam(name = "token") String token, Model model) {
        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);

        if (verificationToken != null) {
            UserEntity user = verificationToken.getUser();
            userService.enableUser(user);
            String successMessage = "会員登録が完了しました。";
            model.addAttribute("successMessage", successMessage);
        } else {
            String errorMessage = "トークンが無効です。";
            model.addAttribute("errorMessage", errorMessage);
        }

        return "auth/verify";
    }

    // クレカ編集画面表示
    @GetMapping("/subscription/edit")
    public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
        StripeEntity stripe = stripeRepository.getReferenceById(userDetailsImpl.getUser().getUserId());
        PaymentMethod paymentMethod = stripeService.getPaymentMethod(stripe.getCustomerId());

        model.addAttribute("card", paymentMethod.getCard());
        model.addAttribute("accountName", paymentMethod.getBillingDetails().getName());

        return "subscription/edit";
    }

    // クレカ情報更新
    @PostMapping("/subscription/update")
    public String update(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @RequestParam String paymentMethodId,
            RedirectAttributes redirectAttributes) {
        StripeEntity stripe = stripeRepository.getReferenceById(userDetailsImpl.getUser().getUserId());

        try {
            stripeService.updateCard(stripe.getCustomerId(), paymentMethodId);
            redirectAttributes.addFlashAttribute("successMessage", "カード情報を変更しました。");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "カード情報の変更に失敗しました。");
        }

        return "redirect:/";
    }

    // サブスク解約画面表示
    @GetMapping("/subscription/cancel")
    public String cancel() {
        return "subscription/cancel";
    }

    // サブスク解約
    @PostMapping("/subscription/delete")
    public String delete(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            RedirectAttributes redirectAttributes) {
        UserEntity user = userRepository.getReferenceById(userDetailsImpl.getUser().getUserId());
        StripeEntity stripe = stripeRepository.getReferenceById(userDetailsImpl.getUser().getUserId());

        Subscription subscription = stripeService.getSubscriptionId(stripe.getCustomerId());
        if (subscription != null) {
            stripeService.cancelSubscription(subscription);
            // 会員ステータスフラグ更新
            userService.cancel(user.getUserId());
            redirectAttributes.addFlashAttribute("successMessage", "有料プランを解約しました。");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "有料プランの解約に失敗しました。");
        }

        return "redirect:/";
    }
}
