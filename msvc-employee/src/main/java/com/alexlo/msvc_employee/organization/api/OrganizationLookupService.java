package com.alexlo.msvc_employee.organization.api;

import com.alexlo.msvc_employee.organization.model.DepartmentEntity;
import com.alexlo.msvc_employee.organization.model.PositionEntity;
import com.alexlo.msvc_employee.organization.repository.DepartmentRepository;
import com.alexlo.msvc_employee.organization.repository.PositionRepository;
import com.alexlo.msvc_employee.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationLookupService {

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    public DepartmentEntity getDepartmentById(Long id){
        return departmentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("No existe departeamento"));
    }

    public PositionEntity getPositionById(Long id){
        return positionRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("No existe cargo"));
    }
}
