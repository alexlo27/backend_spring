package com.alexlo.msvc_employee.employment.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateEmploymentRequestDTO(
        @NotNull Long employeeId,
        @NotNull  Long departmentId,
        @NotNull Long positionId,
        @NotNull LocalDate startDate,
        LocalDate endDate,
        BigDecimal salary,
        Boolean isActive
) {
}
