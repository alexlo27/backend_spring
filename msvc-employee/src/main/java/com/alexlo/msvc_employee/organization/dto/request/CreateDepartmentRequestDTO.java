package com.alexlo.msvc_employee.organization.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateDepartmentRequestDTO(
        @NotBlank String name,
        String code,
        String description,
        Long parentId
) {
}
