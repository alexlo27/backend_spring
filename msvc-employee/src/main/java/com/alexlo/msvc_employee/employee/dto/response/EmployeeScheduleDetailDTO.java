package com.alexlo.msvc_employee.employee.dto.response;

import java.time.LocalDate;

public record EmployeeScheduleDetailDTO(
        Long id,
        String name,
        String lastName,
        DocumentTypeDTO documentType,
        String documentNumber,
        LocalDate birthDate,
        String email,
        String phone,
        String address,
        GenderDTO gender,
        MaritalStatusDTO maritalStatus,
        Boolean isActive,
        DepartmentDTO department,
        PositionDTO position,
        ContractTypeDTO contractType,
        EmployeeTypeDTO employeeType,
        Double totalScheduledHours,
        Integer totalScheduledDays
) {
}
