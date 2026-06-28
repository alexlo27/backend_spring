package com.alexlo.msvc_employee.schedule.dto.request;

import jakarta.validation.constraints.NotNull;

public record ReviewActionRequestDTO(
        Long reviewerEmployeeId,
        @NotNull Long reviewerTypeId,
        String comment
) {
}
