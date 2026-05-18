package com.alexlo.msvc_employee.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateSchedulePeriodRequestDTO(
    @NotNull Long id,
    @NotBlank String period,
    Boolean isActive

) {
}