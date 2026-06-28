package com.alexlo.msvc_employee.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateReviewStatusRequestDTO(
        @NotBlank String code,
        @NotBlank String name,
        Boolean isActive
) {
}
