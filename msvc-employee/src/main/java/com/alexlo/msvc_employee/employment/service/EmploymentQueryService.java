package com.alexlo.msvc_employee.employment.service;

import com.alexlo.msvc_employee.employment.dto.response.EmploymentDTO;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface EmploymentQueryService {

    Map<Long, EmploymentDTO> findCurrentByEmployeeIds(Collection<Long> employeeIds);

    EmploymentDTO findCurrentByEmployeeId(Long employeeId);

    Set<Long> findCurrentEmployeeIdsByDepartment(Long departmentId);
}
