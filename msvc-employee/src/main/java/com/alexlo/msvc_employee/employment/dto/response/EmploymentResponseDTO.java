package com.alexlo.msvc_employee.employment.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmploymentResponseDTO(
        Long id,
        Long employeeId,
        String employeeName,
        String departmentName,
        Long departmentId,
        String positionName,
        Long positionId,
        String employeeTypeName,
        Long employeeTypeId,
        String contractTypeName,
        Long contractTypeId,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal salary,
        Boolean isActive
) {
}
