package com.alexlo.msvc_employee.employee.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateEmployeeDTO(
        @NotNull Long id,
        String name,
        String lastName,
        String documentType,
        String documentNumber,
        LocalDate birthDate,
        @Email String email,
        String phone,
        String address,
        String gender,
        String maritalStatus
) {
}
