package com.codegym.hongheo.core.validator.annotated;


import com.codegym.hongheo.core.validator.ConfirmPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ConfirmPasswordValidator.class)
public @interface ConfirmPassword {
    String message() default "Confirm password doesn't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
