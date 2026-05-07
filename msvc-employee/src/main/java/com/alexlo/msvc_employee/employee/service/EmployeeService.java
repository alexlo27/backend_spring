package com.alexlo.msvc_employee.employee.service;

import com.alexlo.msvc_employee.employee.dto.request.CreateEmployeeDTO;
import com.alexlo.msvc_employee.employee.dto.request.UpdateEmployeeDTO;
import com.alexlo.msvc_employee.employee.dto.response.EmployeeResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO create(CreateEmployeeDTO dto);

    EmployeeResponseDTO update(UpdateEmployeeDTO dto);

    EmployeeResponseDTO findById(Long id);

    List<EmployeeResponseDTO> all();

    PageResponse<EmployeeResponseDTO> all(String name, Pageable pageable);

    void delete(Long id);

}
