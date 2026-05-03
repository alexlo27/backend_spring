package com.alexlo.msvc_employee.employee.validator;

import com.alexlo.msvc_employee.catalog.validator.CatalogLookupService;
import com.alexlo.msvc_employee.employee.dto.request.CreateEmployeeDTO;
import com.alexlo.msvc_employee.employee.dto.request.UpdateEmployeeDTO;
import com.alexlo.msvc_employee.employee.repository.EmployeeRepository;
import com.alexlo.msvc_employee.shared.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmployeeBusinessRules {

    private final CatalogLookupService catalogLookupService;
    private final EmployeeRepository employeeRepository;

    public void createEmployee(CreateEmployeeDTO dto){
        validaUniqueDocumentNumber(dto.documentNumber());
        validaUniqueEmail(dto.email());
    }

    public void updateEmployee(UpdateEmployeeDTO dto){
        validaUniqueDocumentNumber(dto.documentNumber(), dto.id());
        validaUniqueEmail(dto.email(), dto.id());
    }

    private void validaUniqueEmail(String email){
        if (email != null && employeeRepository.existsByEmailIgnoreCase(email)){
            throw new DuplicateResourceException("Email ya existe", "email");
        }
    }

    private void validaUniqueEmail(String email, Long id){
        if (email != null && employeeRepository.existsByEmailIgnoreCaseAndIdNot(email, id)){
            throw new DuplicateResourceException("Email ya existe", "email");
        }
    }

    private void validaUniqueDocumentNumber(String documentNumber){
        if (documentNumber != null && employeeRepository.existsByDocumentNumberIgnoreCase(documentNumber)){
            throw new DuplicateResourceException("El número de documento ya existe", "documentNumber");
        }
    }

    private void validaUniqueDocumentNumber(String documentNumber, Long id){
        if (documentNumber != null && employeeRepository.existsByDocumentNumberIgnoreCaseAndIdNot(documentNumber, id)){
            throw new DuplicateResourceException("El número de documento ya existe", "documentNumber");
        }
    }
}
