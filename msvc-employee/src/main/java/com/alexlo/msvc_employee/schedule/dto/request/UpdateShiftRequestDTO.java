package com.alexlo.msvc_employee.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record UpdateShiftRequestDTO(

    @NotNull Long id,
    @NotBlank String name,
    @NotBlank String code,
    String abbreviation,
    @NotNull LocalTime startTime,
    @NotNull LocalTime endTime,
    LocalTime startBreak,
    LocalTime endBreak,
    Number tolerance,
    LocalTime checkInStartTime,
    LocalTime checkInEndTime,
    LocalTime checkInEndBreak,
    LocalTime lateCheckInLimit,
    Boolean isActive

) {
}