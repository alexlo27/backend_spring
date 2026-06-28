package com.alexlo.msvc_employee.schedule.repository;

import com.alexlo.msvc_employee.schedule.model.ReviewerTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewerTypeRepository extends JpaRepository<ReviewerTypeEntity, Long> {

    Optional<ReviewerTypeEntity> findByCodeIgnoreCase(String code);

    Optional<ReviewerTypeEntity> findByLevel(Integer level);

    boolean existsByCodeIgnoreCase(String code);

    boolean existsByCodeIgnoreCaseAndIdNot(String code, Long id);

    boolean existsByLevelAndIsActiveTrue(Integer level);

    boolean existsByLevelAndIsActiveTrueAndIdNot(Integer level, Long id);

    Page<ReviewerTypeEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
