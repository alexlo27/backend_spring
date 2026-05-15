package com.alexlo.msvc_employee.employment.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateEmployeeTypeRequestDTO(

    @NotBlank String code,
    @NotBlank String name,
    Boolean isActive

) {
}