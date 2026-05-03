package com.alexlo.msvc_employee.organization.service;

import com.alexlo.msvc_employee.organization.dto.request.CreateDepartmentRequestDTO;
import com.alexlo.msvc_employee.organization.dto.request.UpdateDepartmentRequestDTO;
import com.alexlo.msvc_employee.organization.dto.response.DepartmentResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDTO create(CreateDepartmentRequestDTO dto);

    DepartmentResponseDTO update(UpdateDepartmentRequestDTO dto);

    List<DepartmentResponseDTO> all();

    DepartmentResponseDTO findById(Long id);

    PageResponse<DepartmentResponseDTO> all(Pageable pageable);

    void delete(Long id);

}
