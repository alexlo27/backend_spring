package com.alexlo.msvc_user.dto.response;

public record PermissionResponseDTO(
        Long id,
        String name,
        Boolean isActive
) {
}
