package com.alexlo.msvc_employee.schedule.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public record WorkScheduleResponseDTO(
    Long id,
    Long schedulePeriodId,
    Long employeeId,
    Long shiftId,
    LocalDate date,
    LocalTime startTime,
    LocalTime endTime,
    LocalTime startBreak,
    LocalTime endBreak,
    LocalTime checkInTime,
    LocalTime checkOutTime,
    LocalTime breakCheckOutTime,
    LocalTime breakCheckInTime,
    Integer tolerance

) {
}