package com.alexlo.msvc_employee.employment.dto.response;

public record EmploymentDTO(
        Long employeeId,
        Long departmentId,
        String departmentName,
        String departmentCode,
        String departmentDescription,
        Long positionId,
        String positionName,
        Long contractTypeId,
        String contractTypeCode,
        String contractTypeName,
        Long employeeTypeId,
        String employeeTypeCode,
        String employeeTypeName
) {
}
