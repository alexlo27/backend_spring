package com.alexlo.msvc_employee.schedule.dto.response;

import java.time.LocalDateTime;

public record ReviewActionResponseDTO(
        Long id,
        Integer cycle,
        Integer level,
        ReviewerTypeResponseDTO reviewerType,
        Long reviewerEmployeeId,
        ReviewStatusResponseDTO actionStatus,
        String comment,
        LocalDateTime actionAt
) {
}
