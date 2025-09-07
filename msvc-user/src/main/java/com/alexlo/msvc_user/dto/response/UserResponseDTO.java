package com.alexlo.msvc_user.dto.response;

import java.util.Set;

public record UserResponseDTO(
        Long id,
        String email,
        String username,
        String password,
        Set<String>roles
) {
}
