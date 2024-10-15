package com.nissum.user.infrastructure.repository.jpa;

import com.nissum.user.infrastructure.repository.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneJpaRepository extends JpaRepository<PhoneEntity, UUID> {
}
