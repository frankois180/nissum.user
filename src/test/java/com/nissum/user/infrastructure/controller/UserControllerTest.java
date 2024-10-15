package com.nissum.user.infrastructure.controller;

import com.nissum.user.application.create.UserCreate;
import com.nissum.user.domain.exception.RecordExistException;
import com.nissum.user.domain.exception.UserApiNotificationCode;
import com.nissum.user.domain.model.User;
import com.nissum.user.infrastructure.request.PhoneRequest;
import com.nissum.user.infrastructure.request.UserRequest;
import com.nissum.user.infrastructure.respose.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserCreate createUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldReturnUserResponse_WhenValidUserRequest() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("frank@gmail.com");
        userRequest.setName("francisco");
        userRequest.setPassword("****");

        PhoneRequest phone = new PhoneRequest();

        phone.setCityCode("10");
        phone.setNumber("1234566");
        List<PhoneRequest> list = new ArrayList<>();
        list.add(phone);
        userRequest.setPhones(list);

        User user = new User();
        user.setName("francisco");
        user.setId(UUID.randomUUID());
        user.setActive(true);
        user.setToken("jasop756");
        user.setPassword("***");


        when(createUserService.create(any())).thenReturn(user);

        // Act
        UserResponse actualResponse = userController.create(userRequest);

        // Assert
        assertNotNull(actualResponse);
        assertNotNull(actualResponse);
        verify(createUserService, times(1)).create(any());
    }

    @Test
    void create_ShouldThrowRecordExistException_WhenUserExists() {
        // Arrange
        UserRequest userRequest = new UserRequest();
        when(createUserService.create(any())).thenThrow(new RecordExistException(UserApiNotificationCode.DATA_EXIST));

        // Act & Assert
        RecordExistException thrown = assertThrows(RecordExistException.class, () -> userController.create(userRequest));

        assertNotNull(thrown);
        verify(createUserService, times(1)).create(any());
    }
}