package com.nagoyameshi.nagoyameshi.event;

import java.util.UUID;

import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.nagoyameshi.nagoyameshi.entity.UserEntity;
import com.nagoyameshi.nagoyameshi.service.PasswordResetTokenService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PasswordResetEventListener {
    private final PasswordResetTokenService passwordResetTokenService;
    private final JavaMailSender javaMailSender;

    @EventListener
    private void onPasswordResetEvent(PasswordResetEvent passwordResetEvent) {
        UserEntity user = passwordResetEvent.getUser();
        String token = UUID.randomUUID().toString();
        passwordResetTokenService.create(user, token);

        String recipientAddress = user.getEmail();
        String subject = "メール認証";
        String confirmationUrl = passwordResetEvent.getRequestUrl() + "/reset?token=" + token;
        String message = "以下のリンクをクリックしてパスワードを再発行してください。";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientAddress);
        mailMessage.setSubject(subject);
        mailMessage.setText(message + "\n" + confirmationUrl);
        javaMailSender.send(mailMessage);
    }
}
