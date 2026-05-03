package com.alexlo.msvc_employee.organization.dto.response;

public record DepartmentChildDTO(
        Long id,
        String name,
        String code,
        Boolean isActive
) {
}
