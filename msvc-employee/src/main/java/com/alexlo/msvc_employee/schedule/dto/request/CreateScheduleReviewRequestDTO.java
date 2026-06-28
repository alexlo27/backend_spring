package com.alexlo.msvc_employee.schedule.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateScheduleReviewRequestDTO(
        @NotNull Long employeeId,
        @NotNull Long schedulePeriodId
) {
}
