package com.alexlo.msvc_employee.employment.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateEmploymentRequestDTO(
        @NotNull Long id,
        Long employeeId,
        Long departmentId,
        Long positionId,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal salary,
        Boolean isActive
) {
}
