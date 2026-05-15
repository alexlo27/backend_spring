package com.alexlo.msvc_employee.employment.service;

import com.alexlo.msvc_employee.employment.dto.request.CreateEmployeeTypeRequestDTO;
import com.alexlo.msvc_employee.employment.dto.request.UpdateEmployeeTypeRequestDTO;
import com.alexlo.msvc_employee.employment.dto.response.EmployeeTypeResponseDTO;
import com.alexlo.msvc_employee.employment.mapper.EmployeeTypeMapper;
import com.alexlo.msvc_employee.employment.model.EmployeeTypeEntity;
import com.alexlo.msvc_employee.employment.repository.EmployeeTypeRepository;
import com.alexlo.msvc_employee.shared.exception.DuplicateResourceException;
import com.alexlo.msvc_employee.shared.exception.NotFoundException;
import com.alexlo.msvc_employee.shared.mapper.PageMapper;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeTypeServiceImpl implements EmployeeTypeService{

    @Autowired
    EmployeeTypeMapper employeeTypeMapper;

    @Autowired
    EmployeeTypeRepository employeeTypeRepository;

    @Transactional
    @Override
    public EmployeeTypeResponseDTO create(CreateEmployeeTypeRequestDTO dto) {
        if (employeeTypeRepository.existsByCodeIgnoreCase(dto.code())) {
            throw new DuplicateResourceException("El código ya existe", "code");
        }
        return employeeTypeMapper.toResponse(employeeTypeRepository.save(employeeTypeMapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public EmployeeTypeResponseDTO update(UpdateEmployeeTypeRequestDTO dto) {
        if (employeeTypeRepository.existsByCodeIgnoreCaseAndIdNot(dto.code(), dto.id())) {
            throw new DuplicateResourceException("El código ya existe", "code");
        }
        EmployeeTypeEntity employeeType= getEmployeeTypeById(dto.id());
        employeeTypeMapper.updateEntityFromDto(dto, employeeType);
        return employeeTypeMapper.toResponse(employeeTypeRepository.save(employeeType));
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeTypeResponseDTO> all() {
        return employeeTypeMapper.toResponseList(employeeTypeRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<EmployeeTypeResponseDTO> all(String name, Pageable pageable) {
        Page<EmployeeTypeEntity> result = employeeTypeRepository.findByNameContainingIgnoreCase(name, pageable);
        return PageMapper.map(result, employeeTypeMapper::toResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeTypeResponseDTO findById(Long id) {
        return employeeTypeMapper.toResponse(getEmployeeTypeById(id));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getEmployeeTypeById(id);
        employeeTypeRepository.deleteById(id);
    }

    private EmployeeTypeEntity getEmployeeTypeById(Long id){
        return employeeTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo de empleado no encontrado"));
    }

}