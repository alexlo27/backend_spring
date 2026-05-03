package com.alexlo.msvc_employee.organization.validator;

import com.alexlo.msvc_employee.organization.dto.request.CreatePositionRequestDTO;
import com.alexlo.msvc_employee.organization.dto.request.UpdatePositionRequestDTO;
import com.alexlo.msvc_employee.organization.repository.PositionRepository;
import com.alexlo.msvc_employee.shared.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PositionBusinessRules {

    private final PositionRepository positionRepository;

    public void validateCreate(CreatePositionRequestDTO dto){
        validateUniqueName(dto.name());
    }

    public void validateUpdate(UpdatePositionRequestDTO dto){
        validateUniqueName(dto.name(), dto.id());
    }

    private void validateUniqueName(String name){
        if (positionRepository.existsByNameIgnoreCase(name)){
            throw new DuplicateResourceException("El cargo ya existe", "name");
        }
    }
    private void validateUniqueName(String name, Long id) {
        if (positionRepository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new DuplicateResourceException("El cargo ya existe", "name");
        }
    }
}
