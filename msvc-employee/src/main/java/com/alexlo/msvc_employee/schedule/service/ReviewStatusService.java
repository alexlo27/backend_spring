package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.schedule.dto.request.CreateReviewStatusRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateReviewStatusRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ReviewStatusResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewStatusService {

    ReviewStatusResponseDTO create(CreateReviewStatusRequestDTO dto);

    ReviewStatusResponseDTO update(UpdateReviewStatusRequestDTO dto);

    List<ReviewStatusResponseDTO> all();

    PageResponse<ReviewStatusResponseDTO> all(String name, Pageable pageable);

    ReviewStatusResponseDTO findById(Long id);

    void delete(Long id);
}
