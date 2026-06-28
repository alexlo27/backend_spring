package com.alexlo.msvc_employee.schedule.dto.request;

public record UpdateReviewerTypeRequestDTO(
        Long id,
        String code,
        String name,
        Integer level,
        Boolean isActive
) {
}
