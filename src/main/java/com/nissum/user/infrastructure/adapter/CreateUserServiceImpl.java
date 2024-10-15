package com.nissum.user.infrastructure.adapter;

import com.nissum.user.domain.model.User;
import com.nissum.user.domain.port.incoming.create.CreateUserService;
import com.nissum.user.infrastructure.mapper.UserMapper;
import com.nissum.user.infrastructure.repository.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateUserServiceImpl implements CreateUserService {

    private final UserJpaRepository userJpaRepository;

    @Override
    @Transactional
    public User create(User user){
       return UserMapper.mapToUser(userJpaRepository.save(Objects.requireNonNull(UserMapper.mapToUserEntity(user))));
    }





}
