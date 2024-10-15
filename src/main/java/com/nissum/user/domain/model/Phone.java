package com.nissum.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    UUID id;
    UUID userId;
    private String number;
    private String cityCode;
    private String countryCode;
}
