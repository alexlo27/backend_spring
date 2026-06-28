package com.alexlo.msvc_employee.schedule.repository;

import com.alexlo.msvc_employee.schedule.model.ScheduleReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleReviewRepository extends JpaRepository<ScheduleReviewEntity, Long> {

    Optional<ScheduleReviewEntity> findByEmployeeIdAndSchedulePeriodId(Long employeeId, Long schedulePeriodId);
}
