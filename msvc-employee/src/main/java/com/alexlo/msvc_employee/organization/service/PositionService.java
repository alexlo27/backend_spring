package com.alexlo.msvc_employee.organization.service;

import com.alexlo.msvc_employee.organization.dto.request.CreatePositionRequestDTO;
import com.alexlo.msvc_employee.organization.dto.request.UpdatePositionRequestDTO;
import com.alexlo.msvc_employee.organization.dto.response.PositionResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PositionService {

    PositionResponseDTO create(CreatePositionRequestDTO dto);

    PositionResponseDTO update(UpdatePositionRequestDTO dto);

    List<PositionResponseDTO> all();

    PositionResponseDTO findById(Long id);

    PageResponse<PositionResponseDTO> all(Pageable pageable);

    void delete(Long id);

}
