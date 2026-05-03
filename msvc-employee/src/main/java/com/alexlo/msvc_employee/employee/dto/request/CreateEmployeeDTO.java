package com.alexlo.msvc_employee.employee.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateEmployeeDTO(

        @NotBlank String name,
        @NotBlank String lastName,
        @NotBlank String documentType,
        @NotBlank String documentNumber,
        LocalDate birthDate,
        @NotBlank @Email String email,
        String phone,
        String address,
        @NotBlank String gender,

        // ===== Datos laborales =====
        @NotNull(message = "El departamento es obligatorio")
        Long departmentId,

        @NotNull(message = "El cargo es obligatorio")
        Long positionId,

        @NotNull(message = "La fecha de ingreso es obligatoria")
        LocalDate startDate,

        @DecimalMin(value = "0.0", inclusive = false, message = "El salario debe ser mayor a 0")
        BigDecimal salary
) {
}
