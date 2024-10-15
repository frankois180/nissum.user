package com.nissum.user.infrastructure.adapters;

import com.nissum.user.domain.port.incoming.create.CreateTokenService;
import com.nissum.user.infrastructure.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTokenServiceImpl implements CreateTokenService {

    private final JwtUtil jwtUtil;
    @Override
    public String createToken(String name) {
        return jwtUtil.generateToken(name);
    }
}
