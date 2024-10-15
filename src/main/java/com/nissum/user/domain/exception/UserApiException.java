package com.nissum.user.domain.exception;

import lombok.Getter;

public class UserApiException extends RuntimeException {

    @Getter
    private final UserApiNotificationCode notificationCode;

    public UserApiException(String message, UserApiNotificationCode notificationCode) {

        super(message);
        this.notificationCode = notificationCode;

    }

}