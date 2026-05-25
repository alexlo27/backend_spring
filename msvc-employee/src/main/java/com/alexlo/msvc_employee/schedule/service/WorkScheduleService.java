package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.schedule.dto.request.CreateWorkScheduleRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateWorkScheduleRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.WorkScheduleResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WorkScheduleService {

    WorkScheduleResponseDTO create(CreateWorkScheduleRequestDTO dto);

    WorkScheduleResponseDTO update(UpdateWorkScheduleRequestDTO dto);

    List<WorkScheduleResponseDTO> all();

    PageResponse<WorkScheduleResponseDTO> all(Long employeeId, Pageable pageable);

    WorkScheduleResponseDTO findById(Long id);

    List<WorkScheduleResponseDTO> findByEmployeeAndPeriod(Long employeeId, Long schedulePeriodId);

    void delete(Long id);

}