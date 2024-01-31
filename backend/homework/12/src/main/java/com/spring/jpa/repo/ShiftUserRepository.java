package com.spring.jpa.repo;

import com.spring.jpa.entity.ShiftUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftUserRepository extends JpaRepository<ShiftUser, UUID> {
    List<ShiftUser> findAllByTenantId(UUID tenantId);
}
