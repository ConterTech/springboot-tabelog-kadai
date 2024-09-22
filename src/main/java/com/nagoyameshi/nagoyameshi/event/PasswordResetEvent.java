package com.nagoyameshi.nagoyameshi.event;

import org.springframework.context.ApplicationEvent;

import com.nagoyameshi.nagoyameshi.entity.UserEntity;

import lombok.Getter;

@Getter
public class PasswordResetEvent extends ApplicationEvent{
    private UserEntity user;
    private String requestUrl;

    public PasswordResetEvent(Object source, UserEntity user, String requestUrl){
        super(source);

        this.user = user;
        this.requestUrl = requestUrl;
    }
}
