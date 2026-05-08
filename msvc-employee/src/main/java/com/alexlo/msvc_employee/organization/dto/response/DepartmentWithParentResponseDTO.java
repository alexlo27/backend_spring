package com.alexlo.msvc_employee.organization.dto.response;

public record DepartmentWithParentResponseDTO(
        Long id,
        String name,
        String code,
        String description,
        DepartmentParentDTO parent,
        Boolean isActive
) {
}
