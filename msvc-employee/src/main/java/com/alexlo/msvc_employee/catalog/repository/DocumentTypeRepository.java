package com.alexlo.msvc_employee.catalog.repository;

import com.alexlo.msvc_employee.catalog.model.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, Long> {
    boolean existsByCodeIgnoreCase(String code);
    Optional<DocumentTypeEntity> findByCodeIgnoreCase(String code);

    boolean existsByCodeIgnoreCaseAndIdNot(String code, Long id);
}
