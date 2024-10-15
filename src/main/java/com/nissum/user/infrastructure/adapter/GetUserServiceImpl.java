package com.nissum.user.infrastructure.adapter;

import com.nissum.user.domain.port.incoming.search.GetUserService;
import com.nissum.user.infrastructure.repository.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserServiceImpl implements GetUserService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean emailExist(String email) {
        return emailExists(email);
    }


    public boolean emailExists(String email) {

        return userJpaRepository.findByEmail(email).isPresent();
    }
}
