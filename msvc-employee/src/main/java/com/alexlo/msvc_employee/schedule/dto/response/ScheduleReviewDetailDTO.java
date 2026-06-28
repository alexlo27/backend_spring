package com.alexlo.msvc_employee.schedule.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ScheduleReviewDetailDTO(
        Long id,
        Long employeeId,
        Long schedulePeriodId,
        Integer currentCycle,
        Integer currentLevel,
        ReviewStatusResponseDTO status,
        LocalDateTime submittedAt,
        LocalDateTime completedAt,
        List<ReviewActionResponseDTO> actions
) {
}
