package com.alexlo.msvc_employee.employee.service;

import com.alexlo.msvc_employee.employee.dto.response.ContractTypeDTO;
import com.alexlo.msvc_employee.employee.dto.response.DepartmentDTO;
import com.alexlo.msvc_employee.employee.dto.response.DocumentTypeDTO;
import com.alexlo.msvc_employee.employee.dto.response.EmployeeDetailResponseDTO;
import com.alexlo.msvc_employee.employee.dto.response.EmployeeScheduleDetailDTO;
import com.alexlo.msvc_employee.employee.dto.response.EmployeeTypeDTO;
import com.alexlo.msvc_employee.employee.dto.response.GenderDTO;
import com.alexlo.msvc_employee.employee.dto.response.MaritalStatusDTO;
import com.alexlo.msvc_employee.employee.dto.response.PageResponse;
import com.alexlo.msvc_employee.employee.dto.response.PositionDTO;
import com.alexlo.msvc_employee.employee.model.EmployeeEntity;
import com.alexlo.msvc_employee.employee.repository.EmployeeRepository;
import com.alexlo.msvc_employee.employment.dto.response.EmploymentDTO;
import com.alexlo.msvc_employee.employment.service.EmploymentQueryService;
import com.alexlo.msvc_employee.schedule.dto.response.EmployeeScheduleHoursDTO;
import com.alexlo.msvc_employee.schedule.service.ScheduleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EmployeeDetailQueryServiceImpl implements EmployeeDetailQueryService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmploymentQueryService employmentQueryService;

    @Autowired
    private ScheduleQueryService scheduleQueryService;

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
    public PageResponse<EmployeeDetailResponseDTO> findByDepartmentAndEmployeeIds(
            Long departmentId, Set<Long> employeeIds,
            String documentNumber, String fullName, Pageable pageable) {
        Set<Long> deptEmployeeIds = departmentId != null
                ? employmentQueryService.findCurrentEmployeeIdsByDepartment(departmentId)
                : null;

        Set<Long> targetIds;
        if (deptEmployeeIds != null) {
            targetIds = new HashSet<>(deptEmployeeIds);
            targetIds.retainAll(employeeIds);
        } else {
            targetIds = employeeIds;
        }

        if (targetIds == null || targetIds.isEmpty()) {
            return new PageResponse<>(Collections.emptyList(), pageable.getPageNumber(), pageable.getPageSize(), 0, 0, true);
        }

        List<EmployeeEntity> allEmployees = employeeRepository.findByIdIn(targetIds)
                .stream()
                .filter(e -> matchesFilters(e, documentNumber, fullName))
                .toList();

        long total = allEmployees.size();
        List<EmployeeEntity> paged = allEmployees.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .toList();

        List<Long> pagedIds = paged.stream().map(EmployeeEntity::getId).toList();
        Map<Long, EmploymentDTO> employmentMap = employmentQueryService.findCurrentByEmployeeIds(pagedIds);

        List<EmployeeDetailResponseDTO> content = paged.stream()
                .map(emp -> toDetailResponse(emp, employmentMap.get(emp.getId())))
                .toList();

        int totalPages = (int) Math.ceil((double) total / pageable.getPageSize());
        return new PageResponse<>(content, pageable.getPageNumber(), pageable.getPageSize(), total, totalPages, pageable.getPageNumber() >= totalPages - 1);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<EmployeeScheduleDetailDTO> findByDepartmentAndPeriod(
            Long departmentId, Long schedulePeriodId,
            String documentNumber, String fullName, Pageable pageable) {

        Map<Long, EmployeeScheduleHoursDTO> hoursMap = scheduleQueryService.findHoursBySchedulePeriod(schedulePeriodId);
        if (hoursMap.isEmpty()) {
            return new PageResponse<>(Collections.emptyList(), pageable.getPageNumber(), pageable.getPageSize(), 0, 0, true);
        }

        PageResponse<EmployeeDetailResponseDTO> empPage = findByDepartmentAndEmployeeIds(
                departmentId, hoursMap.keySet(), documentNumber, fullName, pageable);

        List<EmployeeScheduleDetailDTO> content = empPage.content().stream()
                .map(dto -> toScheduleDetailResponse(dto, hoursMap.get(dto.id())))
                .toList();

        return new PageResponse<>(content, empPage.page(), empPage.size(), empPage.totalElements(), empPage.totalPages(), empPage.last());
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

    private EmployeeScheduleDetailDTO toScheduleDetailResponse(EmployeeDetailResponseDTO dto, EmployeeScheduleHoursDTO hours) {
        return new EmployeeScheduleDetailDTO(
                dto.id(), dto.name(), dto.lastName(), dto.documentType(),
                dto.documentNumber(), dto.birthDate(), dto.email(), dto.phone(), dto.address(),
                dto.gender(), dto.maritalStatus(), dto.isActive(),
                dto.department(), dto.position(), dto.contractType(), dto.employeeType(),
                hours != null ? hours.totalScheduledHours() : 0.0,
                hours != null ? hours.totalScheduledDays() : 0
        );
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
