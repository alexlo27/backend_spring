package com.alexlo.msvc_employee.catalog.repository;

import com.alexlo.msvc_employee.catalog.model.ContractTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContractTypeRepository extends JpaRepository<ContractTypeEntity, Long> {

    boolean existsByCodeIgnoreCase(String code);
    Optional<ContractTypeEntity> findByCodeIgnoreCase(String code);

    boolean existsByCodeIgnoreCaseAndIdNot(String code, Long id);

    Page<ContractTypeEntity> findByNameContainingIgnoreCase(String name,Pageable pageable);
}