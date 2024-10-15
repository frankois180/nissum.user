package com.nissum.user.infrastructure.api.controller;

import com.nissum.user.application.create.UserCreate;

import javax.validation.Valid;

import com.nissum.user.infrastructure.constant.SwaggerConstant;
import com.nissum.user.infrastructure.mapper.UserMapper;
import com.nissum.user.infrastructure.api.request.UserRequest;
import com.nissum.user.infrastructure.api.respose.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    private final UserCreate userCreate;

    @PostMapping
    @Operation(summary = SwaggerConstant.SWAGGER_CREATE_SUMMARY, description = SwaggerConstant.SWAGGER_CREATE_DESCRIPTION)
    public UserResponse create(@Valid  @RequestBody UserRequest userRequest) {
        return UserMapper.mapToUser(userCreate.create(Objects.requireNonNull(UserMapper.mapToUser(userRequest))));
    }
}
