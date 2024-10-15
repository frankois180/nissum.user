package com.nissum.user.infrastructure.mapper;

import com.nissum.user.domain.model.Phone;
import com.nissum.user.infrastructure.repository.entity.PhoneEntity;
import org.modelmapper.ModelMapper;

public class PhoneMapper {

    public static PhoneEntity mapToPhoneEntity(Phone phone) {
        ModelMapper modelMapper = new ModelMapper();
        if (phone == null) {
            return null;
        }
        return modelMapper.map(phone, PhoneEntity.class);
    }
    public static Phone mapToPhone(PhoneEntity phoneEntity) {
        ModelMapper modelMapper = new ModelMapper();
        if (phoneEntity == null) {
            return null;
        }
        return modelMapper.map(phoneEntity, Phone.class);
    }

}
