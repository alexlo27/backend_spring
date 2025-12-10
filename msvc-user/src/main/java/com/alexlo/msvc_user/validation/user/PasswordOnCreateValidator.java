package com.alexlo.msvc_user.validation.user;

import com.alexlo.msvc_user.dto.request.CreateUserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordOnCreateValidator implements ConstraintValidator< ValidPasswordOnCreate, CreateUserDTO> {
    @Override
    public boolean isValid(CreateUserDTO dto, ConstraintValidatorContext context) {

        if (dto.id() == null){
            boolean isValid = dto.password() != null && !dto.password().isBlank();

            if (!isValid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("La contraseña es obligatoria al crear un nuevo usuario")
                        .addPropertyNode("password")
                        .addConstraintViolation();
            }
            return isValid;
        }
        return true;
    }
}
