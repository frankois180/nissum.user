package com.nissum.user.application.create;

import com.nissum.user.domain.exception.InvalidEmailException;
import com.nissum.user.domain.exception.RecordExistException;
import com.nissum.user.domain.exception.UserApiNotificationCode;
import com.nissum.user.domain.model.Phone;
import com.nissum.user.domain.model.User;
import com.nissum.user.domain.port.incoming.create.CreatePhoneService;
import com.nissum.user.domain.port.incoming.create.CreateTokenService;
import com.nissum.user.domain.port.incoming.create.CreateUserService;
import com.nissum.user.domain.port.incoming.search.GetUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserCreateTest {

    @InjectMocks
    private UserCreate userCreate;

    @Mock
    private CreateUserService createUserService;

    @Mock
    private CreatePhoneService createPhoneService;

    @Mock
    private GetUserService getUserService;

    @Mock
    private CreateTokenService createTokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldSaveUser_WhenEmailIsValidAndNotExists() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        user.setPhones(Arrays.asList(new Phone()));

        when(getUserService.emailExist(user.getEmail())).thenReturn(false);
        when(createTokenService.createToken(any())).thenReturn("mocked-token");
        when(createUserService.create(any())).thenReturn(user);

        // Act
        User savedUser = userCreate.create(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        verify(createUserService, times(1)).create(user);
        verify(createPhoneService, times(1)).create(any(Phone.class));
    }

    @Test
    void create_ShouldThrowRecordExistException_WhenEmailExists() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");

        when(getUserService.emailExist(user.getEmail())).thenReturn(true);

        // Act & Assert
        RecordExistException thrown = assertThrows(RecordExistException.class, () -> userCreate.create(user));

        assertEquals(UserApiNotificationCode.DATA_EXIST.getApiCode(), thrown.getNotificationCode().getApiCode()); // Ajusta según tu implementación
    }

    @Test
    void create_ShouldThrowInvalidEmailException_WhenEmailIsInvalid() {
        // Arrange
        User user = new User();
        user.setEmail("invalid-email");

        when(getUserService.emailExist(user.getEmail())).thenReturn(false);
        when(createTokenService.createToken(any())).thenReturn("mocked-token");

        // Act & Assert
        InvalidEmailException thrown = assertThrows(InvalidEmailException.class, () -> userCreate.create(user));

        assertEquals(UserApiNotificationCode.EMAIL_INVALID.getApiCode(), thrown.getNotificationCode().getApiCode());
    }

    @Test
    void buildUser_ShouldSetUserFields_WhenCalled() {
        // Arrange
        User user = new User();

        // Act
        userCreate.buildUser(user);

        // Assert
        assertNotNull(user.getId());
        assertTrue(user.isActive());
        assertNotNull(user.getCreationDate());
        assertNotNull(user.getModifiedDate());
        assertNotNull(user.getLastLogin());
    }

    @Test
    void savePhones_ShouldCallCreatePhoneService_WhenPhonesAreProvided() {
        // Arrange
        User user = new User();
        user.setId(UUID.randomUUID());
        Phone phone = new Phone();
        phone.setUserId(user.getId());
        user.setPhones(Arrays.asList(phone));

        // Act
        userCreate.savePhones(user.getPhones(), user.getId());

        // Assert
        verify(createPhoneService, times(1)).create(any(Phone.class));
    }
}