package com.alexlo.msvc_employee.employment.service;

import com.alexlo.msvc_employee.employment.dto.request.CreateEmploymentRequestDTO;
import com.alexlo.msvc_employee.employment.dto.request.UpdateEmploymentRequestDTO;
import com.alexlo.msvc_employee.employment.dto.response.EmploymentResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmploymentService {

    EmploymentResponseDTO create(CreateEmploymentRequestDTO dto);

    EmploymentResponseDTO update(UpdateEmploymentRequestDTO dto);

    EmploymentResponseDTO findById(Long id);

    List<EmploymentResponseDTO> all();

    List<EmploymentResponseDTO> findByEmployeeId(Long id);

    PageResponse<EmploymentResponseDTO> all(Pageable pageable);

    PageResponse<EmploymentResponseDTO> findByEmployeeId(Long id, Pageable pageable);

    void delete(Long id);


}
