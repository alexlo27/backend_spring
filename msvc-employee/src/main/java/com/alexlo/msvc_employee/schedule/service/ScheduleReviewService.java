package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.schedule.dto.request.CreateScheduleReviewRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.ReviewActionRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ScheduleReviewDetailDTO;

import java.util.List;

public interface ScheduleReviewService {

    ScheduleReviewDetailDTO submit(CreateScheduleReviewRequestDTO dto);

    ScheduleReviewDetailDTO approve(Long reviewId, ReviewActionRequestDTO dto);

    ScheduleReviewDetailDTO returnReview(Long reviewId, ReviewActionRequestDTO dto);

    ScheduleReviewDetailDTO reject(Long reviewId, ReviewActionRequestDTO dto);

    ScheduleReviewDetailDTO findById(Long reviewId);

    List<ScheduleReviewDetailDTO> findByEmployeeAndPeriod(Long employeeId, Long schedulePeriodId);
}
