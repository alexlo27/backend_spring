package com.alexlo.msvc_user.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordOnCreateValidator.class)
public @interface ValidPasswordOnCreate {

    String message() default "La contraseña es obligatoria al crear un usuario";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
