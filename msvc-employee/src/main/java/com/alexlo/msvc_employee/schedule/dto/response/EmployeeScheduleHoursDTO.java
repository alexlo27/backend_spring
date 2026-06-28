package com.alexlo.msvc_employee.schedule.dto.response;

public record EmployeeScheduleHoursDTO(
        Long employeeId,
        Double totalScheduledHours,
        Integer totalScheduledDays
) {
}
