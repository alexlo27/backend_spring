package com.alexlo.msvc_employee.schedule.dto.response;

public record ReviewStatusResponseDTO(
        Long id,
        String code,
        String name,
        Boolean isActive
) {
}
