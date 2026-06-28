package com.alexlo.msvc_employee.schedule.dto.request;

public record UpdateReviewStatusRequestDTO(
        Long id,
        String code,
        String name,
        Boolean isActive
) {
}
