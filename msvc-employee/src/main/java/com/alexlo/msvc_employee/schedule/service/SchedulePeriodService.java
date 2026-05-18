package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.schedule.dto.request.CreateSchedulePeriodRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateSchedulePeriodRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.SchedulePeriodResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SchedulePeriodService {

    SchedulePeriodResponseDTO create(CreateSchedulePeriodRequestDTO dto);

    SchedulePeriodResponseDTO update(UpdateSchedulePeriodRequestDTO dto);

    List<SchedulePeriodResponseDTO> all();

    PageResponse<SchedulePeriodResponseDTO> all(String period, Pageable pageable);

    SchedulePeriodResponseDTO findById(Long id);

    void delete(Long id);

}