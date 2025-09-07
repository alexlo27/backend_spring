package com.alexlo.msvc_user.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record CreateRoleDTO(
        Long id,
        @NotBlank String name,
        Boolean isActive,
        Set<String> permissions
) {
}
