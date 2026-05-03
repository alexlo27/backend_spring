package com.alexlo.msvc_employee.organization.validator;

import com.alexlo.msvc_employee.organization.dto.request.CreateDepartmentRequestDTO;
import com.alexlo.msvc_employee.organization.dto.request.UpdateDepartmentRequestDTO;
import com.alexlo.msvc_employee.organization.repository.DepartmentRepository;
import com.alexlo.msvc_employee.shared.exception.BadRequestException;
import com.alexlo.msvc_employee.shared.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentBusinessRules {

    private final DepartmentRepository departmentRepository;

    public void validateCreate(CreateDepartmentRequestDTO dto){
        validateUniqueName(dto.name());
        validateUniqueCode(dto.code());
    }

    public void validateUpdate(UpdateDepartmentRequestDTO dto){
        validateUniqueName(dto.name(), dto.id());
        validateUniqueCode(dto.code(), dto.id());
    }

    private void validateUniqueName(String name){
        if (departmentRepository.existsByNameIgnoreCase(name)){
            throw new DuplicateResourceException("El departamento ya existe", "name");
        }
    }

    private void validateUniqueCode(String code){
        if (code != null && departmentRepository.existsByCodeIgnoreCase(code)){
            throw new DuplicateResourceException("El codigo ya existe", "code");
        }
    }

    private void validateUniqueName(String name, Long id) {
        if (name!= null && name.isBlank()){
            throw new BadRequestException("name no puede ser vacio", "name");
        }
        if (name!= null && departmentRepository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new DuplicateResourceException("El departamento ya existe", "name");
        }
    }

    private void validateUniqueCode(String code, Long id) {
        if (code != null && departmentRepository.existsByCodeIgnoreCaseAndIdNot(code, id)) {
            throw new DuplicateResourceException("El departamento ya existe", "name");
        }
    }
}
