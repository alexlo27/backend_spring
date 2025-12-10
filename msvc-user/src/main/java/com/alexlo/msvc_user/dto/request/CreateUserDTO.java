package com.alexlo.msvc_user.dto.request;

import com.alexlo.msvc_user.validation.user.ValidPasswordOnCreate;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@ValidPasswordOnCreate
public record CreateUserDTO(
        Long id,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String username,

        //@NotBlank
        String password,

        Boolean isEnabled,

        Set<String>roles
) {
        /*@AssertTrue(message = "La contraseña es obligatoria al crear un nuevo usuario")
        private boolean isPassword() {
                // Si id es null (creación), password debe estar presente
                if (id == null) {
                        return password != null && !password.trim().isEmpty();
                }
                return true;
        }*/
}
