package com.alexlo.msvc_employee.organization.dto.response;

import java.util.List;

public record DepartmentResponseDTO(
        Long id,
        String name,
        String code,
        String description,
        Long parentId,
        Boolean isActive,
        List<DepartmentResponseDTO> children
) {
}
