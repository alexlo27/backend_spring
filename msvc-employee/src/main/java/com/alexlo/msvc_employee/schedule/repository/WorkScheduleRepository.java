package com.alexlo.msvc_employee.schedule.repository;

import com.alexlo.msvc_employee.schedule.model.WorkScheduleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkScheduleRepository extends JpaRepository<WorkScheduleEntity, Long> {

    List<WorkScheduleEntity> findByEmployeeId(Long employeeId);

    List<WorkScheduleEntity> findBySchedulePeriodId(Long schedulePeriodId);

    List<WorkScheduleEntity> findByEmployeeIdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);

    Page<WorkScheduleEntity> findByEmployeeId(Long employeeId, Pageable pageable);
}