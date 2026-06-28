package com.alexlo.msvc_employee.employee.service;

import com.alexlo.msvc_employee.employee.dto.response.EmployeeDetailResponseDTO;
import com.alexlo.msvc_employee.employee.dto.response.EmployeeScheduleDetailDTO;
import com.alexlo.msvc_employee.employee.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface EmployeeDetailQueryService {

    PageResponse<EmployeeDetailResponseDTO> findAll(Long departmentId, String documentNumber, String fullName, Pageable pageable);

    EmployeeDetailResponseDTO findById(Long id);

    PageResponse<EmployeeScheduleDetailDTO> findByDepartmentAndPeriod(
            Long departmentId,
            Long schedulePeriodId,
            String documentNumber,
            String fullName,
            Pageable pageable
    );

    PageResponse<EmployeeDetailResponseDTO> findByDepartmentAndEmployeeIds(
            Long departmentId,
            Set<Long> employeeIds,
            String documentNumber,
            String fullName,
            Pageable pageable
    );
}
