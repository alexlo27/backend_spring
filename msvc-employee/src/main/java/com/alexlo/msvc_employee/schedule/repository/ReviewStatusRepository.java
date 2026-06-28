package com.alexlo.msvc_employee.schedule.repository;

import com.alexlo.msvc_employee.schedule.model.ReviewStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewStatusRepository extends JpaRepository<ReviewStatusEntity, Long> {

    Optional<ReviewStatusEntity> findByCodeIgnoreCase(String code);

    boolean existsByCodeIgnoreCase(String code);

    boolean existsByCodeIgnoreCaseAndIdNot(String code, Long id);

    Page<ReviewStatusEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
