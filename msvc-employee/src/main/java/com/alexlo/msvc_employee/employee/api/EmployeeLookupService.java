package com.alexlo.msvc_employee.employee.api;

import com.alexlo.msvc_employee.employee.model.EmployeeEntity;
import com.alexlo.msvc_employee.employee.repository.EmployeeRepository;
import com.alexlo.msvc_employee.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmployeeLookupService {

    private final EmployeeRepository employeeRepository;

    public EmployeeEntity getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
    }

}
