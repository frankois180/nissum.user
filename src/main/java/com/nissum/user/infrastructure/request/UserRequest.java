package com.nissum.user.infrastructure.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "El nombre no puede estar vacío.")
    private String name;
    @Email
    private String email;
    @NotBlank
    private String password;
    private List<PhoneRequest> phones;
}
