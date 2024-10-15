package com.nissum.user.domain.port.incoming.create;

import com.nissum.user.domain.model.User;

public interface CreateUserService {

    User create(User user) ;
}
