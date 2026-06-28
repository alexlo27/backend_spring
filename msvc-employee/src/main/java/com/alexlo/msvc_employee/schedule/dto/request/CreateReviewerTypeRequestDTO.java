package com.alexlo.msvc_employee.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateReviewerTypeRequestDTO(
        @NotBlank String code,
        @NotBlank String name,
        @NotNull Integer level,
        Boolean isActive
) {
}
