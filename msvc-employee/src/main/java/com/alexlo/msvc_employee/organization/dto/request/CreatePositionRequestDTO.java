package com.alexlo.msvc_employee.organization.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreatePositionRequestDTO(
        @NotBlank String name
) {
}
