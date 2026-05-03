package com.alexlo.msvc_employee.catalog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateDocumentTypeRequestDTO(
    @NotNull Long id,
    @NotBlank String code,
    @NotBlank String name,
    String description,
    Integer length,
    Boolean isAlphanumeric,
    Boolean isActive

) {
}
