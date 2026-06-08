package com.alexlo.msvc_employee.employee.dto.response;

import java.time.LocalDate;

public record EmployeeDetailRow(
        Long id,
        String name,
        String lastName,
        Long docTypeId,
        String docTypeCode,
        String docTypeName,
        String documentNumber,
        LocalDate birthDate,
        String email,
        String phone,
        String address,
        Long genderId,
        String genderCode,
        String genderName,
        Long maritalStatusId,
        String maritalStatusCode,
        String maritalStatusName,
        Boolean isActive,
        Long deptId,
        String deptName,
        String deptCode,
        String deptDescription,
        Long positionId,
        String positionName,
        Long contractTypeId,
        String contractTypeCode,
        String contractTypeName,
        Long employeeTypeId,
        String employeeTypeCode,
        String employeeTypeName
) {
    public EmployeeDetailResponseDTO toDetailResponse() {
        return new EmployeeDetailResponseDTO(
                id,
                name,
                lastName,
                new DocumentTypeDTO(docTypeId, docTypeCode, docTypeName),
                documentNumber,
                birthDate,
                email,
                phone,
                address,
                new GenderDTO(genderId, genderCode, genderName),
                new MaritalStatusDTO(maritalStatusId, maritalStatusCode, maritalStatusName),
                isActive,
                new DepartmentDTO(deptId, deptName, deptCode, deptDescription),
                new PositionDTO(positionId, positionName),
                new ContractTypeDTO(contractTypeId, contractTypeCode, contractTypeName),
                new EmployeeTypeDTO(employeeTypeId, employeeTypeCode, employeeTypeName)
        );
    }
}