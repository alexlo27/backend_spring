package com.alexlo.msvc_employee.catalog.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateGenderRequestDTO(

    @NotBlank String code,
    @NotBlank String name,
    Boolean isActive

) {
}
