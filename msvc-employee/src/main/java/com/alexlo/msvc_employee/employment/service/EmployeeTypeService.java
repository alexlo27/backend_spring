package com.alexlo.msvc_employee.employment.service;

import com.alexlo.msvc_employee.employment.dto.request.CreateEmployeeTypeRequestDTO;
import com.alexlo.msvc_employee.employment.dto.request.UpdateEmployeeTypeRequestDTO;
import com.alexlo.msvc_employee.employment.dto.response.EmployeeTypeResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeTypeService {

    EmployeeTypeResponseDTO create(CreateEmployeeTypeRequestDTO dto);

    EmployeeTypeResponseDTO update(UpdateEmployeeTypeRequestDTO dto);

    List<EmployeeTypeResponseDTO> all();

    PageResponse<EmployeeTypeResponseDTO> all(String name, Pageable pageable);

    EmployeeTypeResponseDTO findById(Long id);

    void delete(Long id);

}