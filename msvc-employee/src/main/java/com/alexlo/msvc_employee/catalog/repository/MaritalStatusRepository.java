package com.alexlo.msvc_employee.catalog.repository;

import com.alexlo.msvc_employee.catalog.model.MaritalStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaritalStatusRepository extends JpaRepository<MaritalStatusEntity, Long> {

    boolean existsByCodeIgnoreCase(String code);
    Optional<MaritalStatusEntity> findByCodeIgnoreCase(String code);

    boolean existsByCodeIgnoreCaseAndIdNot(String code, Long id);

    Page<MaritalStatusEntity> findByNameContainingIgnoreCase(String name,Pageable pageable);
}