package com.nissum.user.infrastructure.repository.entity;


import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phone")
@Entity
public class PhoneEntity {

    @Id
    @Basic(optional = false)
    @Column(unique = true)
    private UUID id;
    @Column(name = "number")
    private String number;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "user_id")
    private UUID userId;

}