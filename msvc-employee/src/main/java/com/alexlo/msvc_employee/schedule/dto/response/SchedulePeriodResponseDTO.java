package com.alexlo.msvc_employee.schedule.dto.response;

public record SchedulePeriodResponseDTO(
    Long id,
    String period,
    Boolean isActive

) {
}