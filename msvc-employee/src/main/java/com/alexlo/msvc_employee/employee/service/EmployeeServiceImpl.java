package com.alexlo.msvc_employee.employee.service;

import com.alexlo.msvc_employee.catalog.model.DocumentTypeEntity;
import com.alexlo.msvc_employee.catalog.model.GenderEntity;
import com.alexlo.msvc_employee.catalog.validator.CatalogLookupService;
import com.alexlo.msvc_employee.employee.dto.request.CreateEmployeeDTO;
import com.alexlo.msvc_employee.employee.dto.request.UpdateEmployeeDTO;
import com.alexlo.msvc_employee.employee.dto.response.EmployeeResponseDTO;
import com.alexlo.msvc_employee.employee.mapper.EmployeeMapper;
import com.alexlo.msvc_employee.employee.model.EmployeeEntity;
import com.alexlo.msvc_employee.employee.repository.EmployeeRepository;
import com.alexlo.msvc_employee.employee.validator.EmployeeBusinessRules;
import com.alexlo.msvc_employee.employee.validator.EmployeeLookupService;
import com.alexlo.msvc_employee.employment.model.EmploymentEntity;
import com.alexlo.msvc_employee.employment.repository.EmploymentRepository;
import com.alexlo.msvc_employee.organization.validator.OrganizationLookupService;
import com.alexlo.msvc_employee.shared.mapper.PageMapper;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmploymentRepository employmentRepository;

    @Autowired
    CatalogLookupService catalogLookupService;

    @Autowired
    EmployeeBusinessRules employeeBusinessRules;

    @Autowired
    OrganizationLookupService organizationLookupService;

    @Autowired
    EmployeeLookupService employeeLookupService;

    @Transactional
    @Override
    public EmployeeResponseDTO create(CreateEmployeeDTO dto) {
        //return employeeMapper.toResponse(employeeRepository.save(employeeMapper.toEntity(dto)));
        employeeBusinessRules.createEmployee(dto);
        EmployeeEntity employee = employeeMapper.toEntity(dto);

        DocumentTypeEntity documentType = catalogLookupService.getDocumentType(dto.documentType());

        GenderEntity gender = catalogLookupService.getGender(dto.gender());

        employee.setDocumentType(documentType);
        employee.setGender(gender);

        EmployeeEntity saved = employeeRepository.save(employee);

        EmploymentEntity employment = EmploymentEntity.builder()
                .employee(employee)
                .department(organizationLookupService.getDepartmentById(dto.departmentId()))
                .position(organizationLookupService.getPositionById(dto.positionId()))
                .salary(dto.salary())
                .startDate(dto.startDate())
                .isActive(true)
                .build();

        employmentRepository.save(employment);

        return employeeMapper.toResponse(saved);
    }

    @Transactional
    @Override
    public EmployeeResponseDTO update(UpdateEmployeeDTO dto) {
        employeeBusinessRules.updateEmployee(dto);

        EmployeeEntity employee = employeeLookupService.getEmployeeById(dto.id());
        employeeMapper.updateEntityFromDto(dto, employee);
        System.out.println(employee);
        if (dto.documentType() != null){
            DocumentTypeEntity documentType = catalogLookupService.getDocumentType(dto.documentType());
            employee.setDocumentType(documentType);
        }

        if (dto.gender() != null){
            GenderEntity gender = catalogLookupService.getGender(dto.gender());
            employee.setGender(gender);
        }

        EmployeeEntity saved = employeeRepository.save(employee);

        return employeeMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeResponseDTO findById(Long id) {
        return employeeMapper.toResponse(employeeLookupService.getEmployeeById(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeResponseDTO> all() {
        return employeeMapper.toResponseList(employeeRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<EmployeeResponseDTO> all(String name, Pageable pageable) {
        Page<EmployeeEntity> result = employeeRepository.search(name, pageable);
        return PageMapper.map(result, employeeMapper::toResponse);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        employeeLookupService.getEmployeeById(id);
        employeeRepository.deleteById(id);
    }
}
