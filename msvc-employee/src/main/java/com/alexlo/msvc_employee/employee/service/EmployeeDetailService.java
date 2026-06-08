package com.alexlo.msvc_employee.employee.service;

import com.alexlo.msvc_employee.employee.dto.response.EmployeeDetailResponseDTO;
import com.alexlo.msvc_employee.employee.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface EmployeeDetailService {

    PageResponse<EmployeeDetailResponseDTO> findAll(Long departmentId, String documentNumber, String fullName, Pageable pageable);

    EmployeeDetailResponseDTO findById(Long id);
}