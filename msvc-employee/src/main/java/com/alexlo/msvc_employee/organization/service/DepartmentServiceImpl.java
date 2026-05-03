package com.alexlo.msvc_employee.organization.service;

import com.alexlo.msvc_employee.organization.dto.request.CreateDepartmentRequestDTO;
import com.alexlo.msvc_employee.organization.dto.request.UpdateDepartmentRequestDTO;
import com.alexlo.msvc_employee.organization.dto.response.DepartmentResponseDTO;
import com.alexlo.msvc_employee.organization.maper.DepartmentMapper;
import com.alexlo.msvc_employee.organization.model.DepartmentEntity;
import com.alexlo.msvc_employee.organization.repository.DepartmentRepository;
import com.alexlo.msvc_employee.organization.validator.DepartmentBusinessRules;
import com.alexlo.msvc_employee.shared.exception.NotFoundException;
import com.alexlo.msvc_employee.shared.mapper.PageMapper;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final DepartmentBusinessRules rules;

    @Transactional
    @Override
    public DepartmentResponseDTO create(CreateDepartmentRequestDTO dto) {
        rules.validateCreate(dto);
        DepartmentEntity department = departmentMapper.toEntity(dto);

        if (dto.parentId() != null){
            DepartmentEntity parent = getDepartmentById(dto.parentId());
            department.setParent(parent);
        }
        departmentRepository.save(department);
        return departmentMapper.toResponse(department);
    }

    @Transactional
    @Override
    public DepartmentResponseDTO update(UpdateDepartmentRequestDTO dto) {
        rules.validateUpdate(dto);
        DepartmentEntity department = getDepartmentById(dto.id());
        departmentMapper.updateEntityFromDto(dto, department );

        if (dto.parentId() != null){
            DepartmentEntity parent = getDepartmentById(dto.parentId());
            department.setParent(parent);
        } else {
            department.setParent(null);
        }

        departmentRepository.save(department);
        return departmentMapper.toResponse(department);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DepartmentResponseDTO> all() {
        return departmentMapper.toResponseList(departmentRepository.findByParentIsNull());
    }

    @Transactional(readOnly = true)
    @Override
    public DepartmentResponseDTO findById(Long id) {
        return departmentMapper.toResponse(getDepartmentById(id));
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<DepartmentResponseDTO> all(Pageable pageable) {
        Page<DepartmentEntity> result = departmentRepository.findByParentIsNull(pageable);
        return PageMapper.map(result, departmentMapper::toResponse);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getDepartmentById(id);
        departmentRepository.deleteById(id);
    }

    private DepartmentEntity getDepartmentById(Long id){
        return departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Departamento no existe"));
    }
}
