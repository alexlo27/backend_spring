package com.alexlo.msvc_employee.catalog.service;

import com.alexlo.msvc_employee.catalog.dto.request.CreateMaritalStatusRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateMaritalStatusRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.MaritalStatusResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MaritalStatusService {

    MaritalStatusResponseDTO create(CreateMaritalStatusRequestDTO dto);

    MaritalStatusResponseDTO update(UpdateMaritalStatusRequestDTO dto);

    List<MaritalStatusResponseDTO> all();

    PageResponse<MaritalStatusResponseDTO> all(String name, Pageable pageable);

    MaritalStatusResponseDTO findById(Long id);

    void delete(Long id);

}