package com.alexlo.msvc_employee.catalog.dto.response;

public record EmployeeTypeResponseDTO(
    Long id,
    String code,
    String name,
    Boolean isActive

) {
}