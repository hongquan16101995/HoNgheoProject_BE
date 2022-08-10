package com.codegym.hongheo.core.model.dto;

import com.codegym.hongheo.core.validator.annotated.ConfirmPassword;
import com.codegym.hongheo.core.validator.annotated.UniqueUsername;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ConfirmPassword
public class SignUpForm {
    @UniqueUsername
    @NotNull
    @Size(min = 6, max = 20)
    private String username;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @NotNull
    @Size(min = 6, max = 20)
    private String confirmPassword;
}