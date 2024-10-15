package com.nissum.user.domain.exception;

import com.nissum.user.infrastructure.config.UserApiMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum UserApiNotificationCode {

    DATA_EXIST(UserApiMessage.MsgName.DATA_EXIST, "DATA_EXIST"),
    EMAIL_INVALID(UserApiMessage.MsgName.EMAIL_INVALID, "EMAIL_INVALID");

    private UserApiMessage.MsgName message;
    private String apiCode;
}
