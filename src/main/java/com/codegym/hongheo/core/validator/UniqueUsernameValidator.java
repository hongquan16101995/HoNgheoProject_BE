package com.codegym.hongheo.core.validator;


import com.codegym.hongheo.core.repository.IUserRepository;
import com.codegym.hongheo.core.validator.annotated.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private IUserRepository userRepository;

    public UniqueUsernameValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && !userRepository.findByUsername(username).isPresent();
    }
}
