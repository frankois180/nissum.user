package com.nissum.user.infrastructure.adapter;

import com.nissum.user.domain.model.Phone;
import com.nissum.user.domain.port.incoming.create.CreatePhoneService;
import com.nissum.user.infrastructure.mapper.PhoneMapper;
import com.nissum.user.infrastructure.repository.jpa.PhoneJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreatePhoneServiceImpl implements CreatePhoneService {

    private final PhoneJpaRepository phoneJpaRepository;

    @Override
    public Phone create(Phone phone) {
        return PhoneMapper.mapToPhone(phoneJpaRepository.save(Objects.requireNonNull(PhoneMapper.mapToPhoneEntity(phone))));
    }
}
