package com.alexlo.msvc_user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreatePermissionDTO(
        Long id,
        @NotBlank String name,
    Boolean isActive
) {
}
