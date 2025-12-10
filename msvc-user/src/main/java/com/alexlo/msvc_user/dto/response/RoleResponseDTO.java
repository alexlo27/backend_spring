package com.alexlo.msvc_user.dto.response;

import java.util.List;
import java.util.Set;

public record RoleResponseDTO(
        Long id,
        String name,
        Boolean isActive,
        Set<String> permissions
) {
}
