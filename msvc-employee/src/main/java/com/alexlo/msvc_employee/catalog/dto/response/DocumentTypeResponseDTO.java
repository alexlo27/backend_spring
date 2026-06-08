package com.alexlo.msvc_employee.catalog.dto.response;

public record DocumentTypeResponseDTO(
    Long id,
    String code,
    String name,
    String description,
    Integer length,
    Boolean isAlphanumeric,
    Boolean isActive

) {
}
