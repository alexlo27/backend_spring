package com.alexlo.msvc_employee.schedule.repository;

import com.alexlo.msvc_employee.schedule.model.SchedulePeriodEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchedulePeriodRepository extends JpaRepository<SchedulePeriodEntity, Long> {

    boolean existsByPeriodIgnoreCase(String period);
    Optional<SchedulePeriodEntity> findByPeriodIgnoreCase(String period);

    boolean existsByPeriodIgnoreCaseAndIdNot(String period, Long id);

    Page<SchedulePeriodEntity> findByPeriodContainingIgnoreCase(String period, Pageable pageable);
}