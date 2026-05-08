package com.alexlo.msvc_employee.catalog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateContractTypeRequestDTO(
    @NotNull Long id,
    @NotBlank String code,
    @NotBlank String name,
    Boolean isActive

) {
}