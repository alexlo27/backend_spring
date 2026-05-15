package com.alexlo.msvc_employee.employment.dto.response;

public record EmployeeTypeResponseDTO(
    Long id,
    String code,
    String name,
    Boolean isActive

) {
}