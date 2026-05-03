package com.alexlo.msvc_employee.organization.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateDepartmentRequestDTO(
        @NotNull Long id,
        String name,
        String code,
        String description,
        Long parentId
) {
}
