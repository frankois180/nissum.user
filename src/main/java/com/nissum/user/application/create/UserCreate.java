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
import com.nissum.user.infrastructure.util.EmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserCreate {

    private final CreateUserService createUserService;
    private final CreatePhoneService createPhoneService;
    private final GetUserService getUserService;
    private final CreateTokenService createTokenService;

    public User create(User user) {
        String email = user.getEmail();
        if(email != null) {
            emailNotValid(email);
            existEmail(email);
        }
        buildUser(user);
        User userSave = createUserService.create(user);
        savePhones(user.getPhones(), user.getId());

        return userSave;
    }

    public void savePhones(List<Phone> phones, UUID userID) {
        phones.forEach(phone -> {
            phone.setUserId(userID);
            phone.setId(UUID.randomUUID());
            createPhoneService.create(Objects.requireNonNull(phone));
        });
    }

    private void existEmail(String email) {
        if (getUserService.emailExist(email)) {
            throw new RecordExistException(UserApiNotificationCode.DATA_EXIST, email);
        }
    }

    public void buildUser(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setToken(createTokenService.createToken(user.getName()));
        user.setId(UUID.randomUUID());
        user.setActive(true);
        user.setCreationDate(now);
        user.setLastLogin(now);
    }

    private void emailNotValid (String email){
        if(!EmailValidator.isValidEmail(email)){
            throw new InvalidEmailException(UserApiNotificationCode.EMAIL_INVALID, email);
        }
    }
}
