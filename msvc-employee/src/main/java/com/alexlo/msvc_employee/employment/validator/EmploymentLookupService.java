package com.alexlo.msvc_employee.employment.validator;

import com.alexlo.msvc_employee.employment.model.EmploymentEntity;
import com.alexlo.msvc_employee.employment.repository.EmploymentRepository;
import com.alexlo.msvc_employee.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmploymentLookupService {

    private final EmploymentRepository employmentRepository;

    public EmploymentEntity getEmploymentById(Long id){
        return employmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empleo no encontrado"));
    }
}
