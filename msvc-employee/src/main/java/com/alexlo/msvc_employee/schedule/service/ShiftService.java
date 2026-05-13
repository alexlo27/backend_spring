package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.schedule.dto.request.CreateShiftRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateShiftRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ShiftResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShiftService {

    ShiftResponseDTO create(CreateShiftRequestDTO dto);

    ShiftResponseDTO update(UpdateShiftRequestDTO dto);

    List<ShiftResponseDTO> all();

    PageResponse<ShiftResponseDTO> all(String name, Pageable pageable);

    ShiftResponseDTO findById(Long id);

    void delete(Long id);

}