package com.alexlo.msvc_employee.catalog.dto.response;

public record GenderResponseDTO(
    Long id,
    String code,
    String name,
    Boolean isActive

) {
}
