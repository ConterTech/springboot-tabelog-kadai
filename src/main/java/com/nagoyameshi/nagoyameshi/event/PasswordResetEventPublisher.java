package com.nagoyameshi.nagoyameshi.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.nagoyameshi.nagoyameshi.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PasswordResetEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishResetEvent(UserEntity user, String requestUrl){
        applicationEventPublisher.publishEvent(new PasswordResetEvent(this, user, requestUrl));
    }
}
