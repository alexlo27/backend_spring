package com.alexlo.msvc_employee.schedule.dto.response;

import java.time.LocalTime;

public record ShiftResponseDTO(
    Long id,
    String name,
    String code,
    String abbreviation,
    LocalTime startTime,
    LocalTime endTime,
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