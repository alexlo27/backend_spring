package com.alexlo.msvc_employee.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateSchedulePeriodRequestDTO(

    @NotBlank String period,
    Boolean isActive

) {
}