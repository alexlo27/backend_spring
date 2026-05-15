package com.alexlo.msvc_employee.employment.repository;

import com.alexlo.msvc_employee.employment.model.EmployeeTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeTypeRepository extends JpaRepository<EmployeeTypeEntity, Long> {

    boolean existsByCodeIgnoreCase(String code);
    Optional<EmployeeTypeEntity> findByCodeIgnoreCase(String code);

    boolean existsByCodeIgnoreCaseAndIdNot(String code, Long id);

    Page<EmployeeTypeEntity> findByNameContainingIgnoreCase(String name,Pageable pageable);
}