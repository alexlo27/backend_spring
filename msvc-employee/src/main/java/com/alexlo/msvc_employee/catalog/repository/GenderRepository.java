package com.alexlo.msvc_employee.catalog.repository;

import com.alexlo.msvc_employee.catalog.model.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepository extends JpaRepository<GenderEntity, Long> {

    boolean existsByCodeIgnoreCase(String code);
    Optional<GenderEntity> findByCodeIgnoreCase(String code);

    boolean existsByCodeIgnoreCaseAndIdNot(String code, Long id);
}
