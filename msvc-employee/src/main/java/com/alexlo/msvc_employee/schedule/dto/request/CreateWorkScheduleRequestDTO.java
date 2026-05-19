package com.alexlo.msvc_employee.schedule.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateWorkScheduleRequestDTO(

    @NotNull Long schedulePeriodId,
    @NotNull Long employeeId,
    Long shiftId,
    @NotNull LocalDate date,
    @NotNull LocalTime startTime,
    @NotNull LocalTime endTime,
    LocalTime startBreak,
    LocalTime endBreak,
    LocalTime checkInTime,
    LocalTime checkOutTime,
    LocalTime breakCheckOutTime,
    LocalTime breakCheckInTime,
    Integer tolerance

) {
}