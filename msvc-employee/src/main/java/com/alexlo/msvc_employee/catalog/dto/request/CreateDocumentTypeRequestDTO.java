package com.alexlo.msvc_employee.catalog.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateDocumentTypeRequestDTO(
    @NotBlank String code,
    @NotBlank String name,
    String description,
    Integer length,
    Boolean isAlphanumeric,
    Boolean isActive

) {
}
