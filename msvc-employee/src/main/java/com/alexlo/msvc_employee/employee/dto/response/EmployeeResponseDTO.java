package com.alexlo.msvc_employee.employee.dto.response;

import java.time.LocalDate;

public record EmployeeResponseDTO(
        Long id,
        String name,
        String lastName,
        String documentType,
        String documentNumber,
        LocalDate birthDate,
        String email,
        String phone,
        String address,
        String gender,
        String maritalStatus,
        Boolean isActive
) {
}
