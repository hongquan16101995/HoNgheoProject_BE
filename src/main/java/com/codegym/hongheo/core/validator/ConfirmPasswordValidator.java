package com.codegym.hongheo.core.validator;


import com.codegym.hongheo.core.model.dto.SignUpForm;
import com.codegym.hongheo.core.validator.annotated.ConfirmPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {
    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext arg1) {
        SignUpForm signUpForm = (SignUpForm) candidate;
        return signUpForm.getPassword().equals(signUpForm.getConfirmPassword());
    }
}
