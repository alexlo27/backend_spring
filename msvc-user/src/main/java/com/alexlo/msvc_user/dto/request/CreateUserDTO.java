package com.alexlo.msvc_user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record CreateUserDTO(
        Long id,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String username,

        @NotBlank
        String password,

        Set<String>roles
) {
}
