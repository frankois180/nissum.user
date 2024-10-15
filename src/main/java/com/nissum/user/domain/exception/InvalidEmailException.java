package com.nissum.user.domain.exception;

import com.nissum.user.infrastructure.config.UserApiMessage;

public class InvalidEmailException extends UserApiException {


    public InvalidEmailException(UserApiNotificationCode notificationCode, Object... param) {
        super(UserApiMessage.msg(notificationCode.getMessage(), param), notificationCode);
    }
}
