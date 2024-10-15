package com.nissum.user.infrastructure.mapper;

import com.nissum.user.domain.model.User;
import com.nissum.user.infrastructure.repository.entity.UserEntity;
import com.nissum.user.infrastructure.request.UserRequest;
import com.nissum.user.infrastructure.respose.UserResponse;
import org.modelmapper.ModelMapper;

public class UserMapper {

    public static UserEntity mapToUserEntity(User user) {
        ModelMapper modelMapper = new ModelMapper();
        if (user == null) {
            return null;
        }
        return modelMapper.map(user, UserEntity.class);
    }

    public static User mapToUser(UserEntity userEntity) {
        ModelMapper modelMapper = new ModelMapper();
        if (userEntity == null) {
            return null;
        }
        return modelMapper.map(userEntity, User.class);
    }

    public static UserResponse mapToUser(User user) {
        ModelMapper modelMapper = new ModelMapper();
        if (user == null) {
            return null;
        }
        return modelMapper.map(user, UserResponse.class);
    }

    public static User mapToUser(UserRequest userRequest) {
        ModelMapper modelMapper = new ModelMapper();
        if (userRequest == null) {
            return null;
        }
        return modelMapper.map(userRequest, User.class);
    }
}
