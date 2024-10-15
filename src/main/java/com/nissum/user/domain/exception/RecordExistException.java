package com.nissum.user.domain.exception;

import com.nissum.user.infrastructure.config.UserApiMessage;

public class RecordExistException extends UserApiException {


    public RecordExistException(UserApiNotificationCode notificationCode, Object... param) {
        super(UserApiMessage.msg(notificationCode.getMessage(), param), notificationCode);
    }
}
