package com.alexlo.msvc_employee.catalog.service;

import com.alexlo.msvc_employee.catalog.dto.request.CreateGenderRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.request.UpdateGenderRequestDTO;
import com.alexlo.msvc_employee.catalog.dto.response.GenderResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenderService {

    GenderResponseDTO create(CreateGenderRequestDTO dto);

    GenderResponseDTO update(UpdateGenderRequestDTO dto);

    List<GenderResponseDTO> all();

    PageResponse<GenderResponseDTO> all(Pageable pageable);

    GenderResponseDTO findById(Long id);

    void delete(Long id);

}
