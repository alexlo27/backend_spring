package com.alexlo.msvc_employee.catalog.service;

import com.alexlo.msvc_employee.catalog.dto.request.CreateEmployeeTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateEmployeeTypeRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.EmployeeTypeResponseDTO;
import com.alexlo.msvc_employee.catalog.maper.EmployeeTypeMapper;
import com.alexlo.msvc_employee.catalog.model.EmployeeTypeEntity;
import com.alexlo.msvc_employee.catalog.repository.EmployeeTypeRepository;
import com.alexlo.msvc_employee.catalog.validator.CatalogLookupService;
import com.alexlo.msvc_employee.shared.exception.DuplicateResourceException;
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

    @Autowired
    CatalogLookupService catalogLookupService;

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
        EmployeeTypeEntity employeeType= catalogLookupService.getEmployeeTypeById(dto.id());
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
        return employeeTypeMapper.toResponse(catalogLookupService.getEmployeeTypeById(id));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        catalogLookupService.getEmployeeTypeById(id);
        employeeTypeRepository.deleteById(id);
    }


}