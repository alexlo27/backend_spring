package com.alexlo.msvc_employee.schedule.dto.response;

public record ReviewerTypeResponseDTO(
        Long id,
        String code,
        String name,
        Integer level,
        Boolean isActive
) {
}
