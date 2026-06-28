package com.alexlo.msvc_employee.schedule.repository;

import com.alexlo.msvc_employee.schedule.model.ReviewActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewActionRepository extends JpaRepository<ReviewActionEntity, Long> {

    List<ReviewActionEntity> findByScheduleReviewIdOrderByCycleDescLevelDesc(Long scheduleReviewId);
}
