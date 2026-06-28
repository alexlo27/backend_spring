package com.alexlo.msvc_employee.schedule.service;

import com.alexlo.msvc_employee.schedule.dto.request.CreateReviewerTypeRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.request.UpdateReviewerTypeRequestDTO;
import com.alexlo.msvc_employee.schedule.dto.response.ReviewerTypeResponseDTO;
import com.alexlo.msvc_employee.shared.mapper.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewerTypeService {

    ReviewerTypeResponseDTO create(CreateReviewerTypeRequestDTO dto);

    ReviewerTypeResponseDTO update(UpdateReviewerTypeRequestDTO dto);

    List<ReviewerTypeResponseDTO> all();

    PageResponse<ReviewerTypeResponseDTO> all(String name, Pageable pageable);

    ReviewerTypeResponseDTO findById(Long id);

    void delete(Long id);
}
