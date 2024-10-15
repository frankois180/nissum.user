package com.nissum.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private boolean isActive;
    private  String token;
    private LocalDateTime modifiedDate;
    private LocalDateTime creationDate;
    private LocalDateTime lastLogin;
    private List<Phone> phones;
}
