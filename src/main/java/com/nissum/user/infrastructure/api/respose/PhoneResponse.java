package com.nissum.user.infrastructure.api.respose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneResponse {

    private String number;
    private String cityCode;
    private String countryCode;
}
