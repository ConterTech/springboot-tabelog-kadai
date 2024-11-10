package com.nagoyameshi.nagoyameshi.service;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagoyameshi.nagoyameshi.entity.RoleEntity;
import com.nagoyameshi.nagoyameshi.entity.StripeEntity;
import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.form.PasswordResetForm;
import com.nagoyameshi.nagoyameshi.form.SignupForm;
import com.nagoyameshi.nagoyameshi.form.UserEditForm;
import com.nagoyameshi.nagoyameshi.repository.RoleRepository;
import com.nagoyameshi.nagoyameshi.repository.StripeRepository;
import com.nagoyameshi.nagoyameshi.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StripeRepository stripeRepository;
    private final PasswordEncoder passwordEncoder;

    // ユーザ登録
    @Transactional
    public UserEntity create(SignupForm signupForm) {
        UserEntity user = new UserEntity();
        RoleEntity role = roleRepository.findByName("ROLE_GENERAL");

        user.setName(signupForm.getName());
        user.setPhoneNumber(signupForm.getPhoneNumber());
        user.setPostCode(signupForm.getPostCode());
        user.setAddress(signupForm.getAddress());
        user.setEmail(signupForm.getEmail());
        user.setAge(signupForm.getAge());
        user.setGender(signupForm.getGender());
        user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        user.setRole(role);
        user.setEnabled(false);

        return userRepository.save(user);
    }

    // ユーザ編集
    @Transactional
    public void update(UserEditForm userEditForm) {
        UserEntity user = userRepository.getReferenceById(userEditForm.getUserId());

        user.setName(userEditForm.getName());
        user.setPhoneNumber(userEditForm.getPhoneNumber());
        user.setPostCode(userEditForm.getPostCode());
        user.setAddress(userEditForm.getAddress());
        user.setEmail(userEditForm.getEmail());
        user.setAge(userEditForm.getAge());
        user.setGender(userEditForm.getGender());

        userRepository.save(user);
    }

    // パスワード変更
    @Transactional
    public void updatePassword(PasswordResetForm passwordResetForm){
        UserEntity user = userRepository.getReferenceById(passwordResetForm.getUserId());

        user.setPassword(passwordEncoder.encode(passwordResetForm.getPassword()));

        userRepository.save(user);
    }

    // 有料会員フラグ有効化 データベース格納
    @Transactional
    public void register(Map<String, String> paymentIntentObject, String customerId) {
        StripeEntity stripe = new StripeEntity();
        Integer userId = Integer.valueOf(paymentIntentObject.get("userId"));

        stripe.setUserId(userId);
        stripe.setCustomerId(customerId);

        UserEntity user = userRepository.getReferenceById(userId);
        user.setPaidFlag(true);

        stripeRepository.save(stripe);
        userRepository.save(user);
    }

    // 有料会員フラグ無効化
    @Transactional
    public void cancel(Integer userId) {
        // フラグ変更有料→無料
        UserEntity user = userRepository.getReferenceById(userId);
        user.setPaidFlag(false);
        userRepository.save(user);
        // StripeEntity情報削除
        stripeRepository.deleteById(userId);
    }

    // メールアドレスが登録済みかどうかチェックする
    public boolean isEmailRegistered(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user != null;
    }

    // パスワードとパスワード（確認用）の入力値が一致するかどうかをチェックする
    public boolean isSamePassword(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }

    // ユーザを有効にする
    @Transactional
    public void enableUser(UserEntity user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    // メールアドレスが変更されたかどうかをチェックする
    public boolean isEmailChanged(UserEditForm userEditForm) {
        UserEntity currentUser = userRepository.getReferenceById(userEditForm.getUserId());
        return !userEditForm.getEmail().equals(currentUser.getEmail());
    }
}
