package com.alexlo.msvc_employee.employee.service;

import com.alexlo.msvc_employee.employee.dto.response.ContractTypeDTO;
import com.alexlo.msvc_employee.employee.dto.response.DepartmentDTO;
import com.alexlo.msvc_employee.employee.dto.response.DocumentTypeDTO;
import com.alexlo.msvc_employee.employee.dto.response.EmployeeDetailResponseDTO;
import com.alexlo.msvc_employee.employee.dto.response.EmployeeTypeDTO;
import com.alexlo.msvc_employee.employee.dto.response.GenderDTO;
import com.alexlo.msvc_employee.employee.dto.response.MaritalStatusDTO;
import com.alexlo.msvc_employee.employee.dto.response.PageResponse;
import com.alexlo.msvc_employee.employee.dto.response.PositionDTO;
import com.alexlo.msvc_employee.employee.model.EmployeeEntity;
import com.alexlo.msvc_employee.employee.repository.EmployeeRepository;
import com.alexlo.msvc_employee.employment.dto.response.EmploymentDTO;
import com.alexlo.msvc_employee.employment.service.EmploymentQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeDetailQueryServiceImpl implements EmployeeDetailQueryService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmploymentQueryService employmentQueryService;

    @Transactional(readOnly = true)
    @Override
    public PageResponse<EmployeeDetailResponseDTO> findAll(Long departmentId, String documentNumber, String fullName, Pageable pageable) {
        Page<EmployeeEntity> employeePage;
        Map<Long, EmploymentDTO> employmentMap;

        if (departmentId != null) {
            Set<Long> employeeIds = employmentQueryService.findCurrentEmployeeIdsByDepartment(departmentId);
            if (employeeIds.isEmpty()) {
                return new PageResponse<>(Collections.emptyList(), 0, pageable.getPageSize(), 0, 0, true);
            }
            long total = employeeRepository.countByIdIn(employeeIds);
            List<EmployeeEntity> employees = employeeRepository.findByIdIn(employeeIds);
            List<EmployeeEntity> filtered = employees.stream()
                    .filter(e -> matchesFilters(e, documentNumber, fullName))
                    .skip(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .toList();
            employeePage = new PageImpl<>(filtered, pageable, total);

            List<Long> ids = filtered.stream().map(EmployeeEntity::getId).toList();
            employmentMap = employmentQueryService.findCurrentByEmployeeIds(ids);
        } else {
            employeePage = employeeRepository.findByFilters(documentNumber, fullName, pageable);
            List<Long> ids = employeePage.getContent().stream().map(EmployeeEntity::getId).toList();
            employmentMap = employmentQueryService.findCurrentByEmployeeIds(ids);
        }

        List<EmployeeDetailResponseDTO> content = employeePage.getContent().stream()
                .map(emp -> toDetailResponse(emp, employmentMap.get(emp.getId())))
                .toList();

        return new PageResponse<>(
                content,
                employeePage.getNumber(),
                employeePage.getSize(),
                employeePage.getTotalElements(),
                employeePage.getTotalPages(),
                employeePage.isLast()
        );
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeDetailResponseDTO findById(Long id) {
        EmploymentDTO employment = employmentQueryService.findCurrentByEmployeeId(id);
        return employeeRepository.findById(id)
                .map(emp -> toDetailResponse(emp, employment))
                .orElse(null);
    }

    private boolean matchesFilters(EmployeeEntity e, String documentNumber, String fullName) {
        if (documentNumber != null && !documentNumber.isBlank()) {
            if (!e.getDocumentNumber().toLowerCase().contains(documentNumber.toLowerCase())) {
                return false;
            }
        }
        if (fullName != null && !fullName.isBlank()) {
            String full = (e.getName() + " " + e.getLastName()).toLowerCase();
            if (!full.contains(fullName.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    private EmployeeDetailResponseDTO toDetailResponse(EmployeeEntity entity, EmploymentDTO employment) {
        return new EmployeeDetailResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                new DocumentTypeDTO(
                        entity.getDocumentType().getId(),
                        entity.getDocumentType().getCode(),
                        entity.getDocumentType().getName()
                ),
                entity.getDocumentNumber(),
                entity.getBirthDate(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                new GenderDTO(
                        entity.getGender().getId(),
                        entity.getGender().getCode(),
                        entity.getGender().getName()
                ),
                new MaritalStatusDTO(
                        entity.getMaritalStatus().getId(),
                        entity.getMaritalStatus().getCode(),
                        entity.getMaritalStatus().getName()
                ),
                entity.getIsActive(),
                employment != null ? new DepartmentDTO(
                        employment.departmentId(),
                        employment.departmentName(),
                        employment.departmentCode(),
                        employment.departmentDescription()
                ) : null,
                employment != null ? new PositionDTO(
                        employment.positionId(),
                        employment.positionName()
                ) : null,
                employment != null && employment.contractTypeId() != null
                        ? new ContractTypeDTO(
                        employment.contractTypeId(),
                        employment.contractTypeCode(),
                        employment.contractTypeName()
                ) : null,
                employment != null && employment.employeeTypeId() != null
                        ? new EmployeeTypeDTO(
                        employment.employeeTypeId(),
                        employment.employeeTypeCode(),
                        employment.employeeTypeName()
                ) : null
        );
    }
}
