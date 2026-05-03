package com.alexlo.msvc_employee.organization.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePositionRequestDTO(
        @NotNull Long id,
        @NotBlank String name,
        Boolean isActive
) {
}
