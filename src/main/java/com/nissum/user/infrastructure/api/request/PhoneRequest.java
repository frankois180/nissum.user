package com.nissum.user.infrastructure.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequest {
    @NotBlank
    private String number;
    @NotBlank
    private String cityCode;
    @NotBlank
    private String countryCode;
}
