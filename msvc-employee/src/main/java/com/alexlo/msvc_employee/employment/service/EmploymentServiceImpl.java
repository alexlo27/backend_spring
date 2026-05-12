package com.alexlo.msvc_employee.employment.service;

import com.alexlo.msvc_employee.catalog.model.ContractTypeEntity;
import com.alexlo.msvc_employee.catalog.model.EmployeeTypeEntity;
import com.alexlo.msvc_employee.catalog.validator.CatalogLookupService;
import com.alexlo.msvc_employee.employee.mapper.EmployeeMapper;
import com.alexlo.msvc_employee.employee.model.EmployeeEntity;
import com.alexlo.msvc_employee.employee.validator.EmployeeLookupService;
import com.alexlo.msvc_employee.employment.dto.request.CreateEmploymentRequestDTO;
import com.alexlo.msvc_employee.employment.dto.request.UpdateEmploymentRequestDTO;
import com.alexlo.msvc_employee.employment.dto.response.EmploymentResponseDTO;
import com.alexlo.msvc_employee.employment.mapper.EmploymentMapper;
import com.alexlo.msvc_employee.employment.model.EmploymentEntity;
import com.alexlo.msvc_employee.employment.repository.EmploymentRepository;
import com.alexlo.msvc_employee.employment.validator.EmploymentLookupService;
import com.alexlo.msvc_employee.organization.model.DepartmentEntity;
import com.alexlo.msvc_employee.organization.model.PositionEntity;
import com.alexlo.msvc_employee.organization.validator.OrganizationLookupService;
import com.alexlo.msvc_employee.shared.mapper.PageMapper;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmploymentServiceImpl implements EmploymentService{

    private final EmploymentRepository employmentRepository;
    private final EmploymentMapper employmentMapper;
    private final OrganizationLookupService organizationLookupService;
    private final EmployeeLookupService employeeLookupService;
    private final EmploymentLookupService employmentLookupService;
    private final CatalogLookupService catalogLookupService;

    @Override
    public EmploymentResponseDTO create(CreateEmploymentRequestDTO dto) {

        EmploymentEntity employment = employmentMapper.toEntity(dto);
        EmployeeEntity employee = employeeLookupService.getEmployeeById(dto.employeeId());
        DepartmentEntity department = organizationLookupService.getDepartmentById(dto.departmentId());
        PositionEntity position = organizationLookupService.getPositionById(dto.positionId());
        EmployeeTypeEntity employeeType = catalogLookupService.getEmployeeTypeById(dto.employeeTypeId());
        ContractTypeEntity contractType = catalogLookupService.getContractTypeById(dto.contractTypeId());
        employment.setEmployee(employee);
        employment.setDepartment(department);
        employment.setPosition(position);
        employment.setContractType(contractType);
        employment.setEmployeeType(employeeType);

        return employmentMapper.toResponse(employmentRepository.save(employment));
    }

    @Override
    public EmploymentResponseDTO update(UpdateEmploymentRequestDTO dto) {
        EmploymentEntity employment = employmentLookupService.getEmploymentById(dto.id());
        employmentMapper.updateEntityFromDto(dto, employment);

        if (dto.employeeId() != null){
            EmployeeEntity employee = employeeLookupService.getEmployeeById(dto.employeeId());
            employment.setEmployee(employee);
        }

        if (dto.departmentId() != null){
            DepartmentEntity department = organizationLookupService.getDepartmentById(dto.departmentId());
            employment.setDepartment(department);
        }

        if (dto.positionId() != null){
            PositionEntity position = organizationLookupService.getPositionById(dto.positionId());
            employment.setPosition(position);
        }

        if(dto.employeeTypeId() != null){
            EmployeeTypeEntity employeeType = catalogLookupService.getEmployeeTypeById(dto.employeeTypeId());
            employment.setEmployeeType(employeeType);
        }

        if(dto.contractTypeId() != null){
            ContractTypeEntity contractType = catalogLookupService.getContractTypeById(dto.contractTypeId());
            employment.setContractType(contractType);
        }

        return employmentMapper.toResponse( employmentRepository.save(employment));
    }

    @Override
    public EmploymentResponseDTO findById(Long id) {
        return employmentMapper.toResponse(employmentLookupService.getEmploymentById(id));
    }

    @Override
    public List<EmploymentResponseDTO> all() {
        return employmentMapper.toResponseList(employmentRepository.findAll());
    }

    @Override
    public List<EmploymentResponseDTO> findByEmployeeId(Long id) {
        return employmentMapper.toResponseList(employmentRepository.findByEmployeeId(id));
    }

    @Override
    public PageResponse<EmploymentResponseDTO> all(Pageable pageable) {
        Page<EmploymentEntity> result = employmentRepository.findAll(pageable);
        return PageMapper.map(result, employmentMapper::toResponse);
    }

    @Override
    public PageResponse<EmploymentResponseDTO> findByEmployeeId(Long id, Pageable pageable) {
        Page<EmploymentEntity> result = employmentRepository.findByEmployeeId(id, pageable);
        return PageMapper.map(result, employmentMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        employmentLookupService.getEmploymentById(id);
        employmentRepository.deleteById(id);
    }
}
