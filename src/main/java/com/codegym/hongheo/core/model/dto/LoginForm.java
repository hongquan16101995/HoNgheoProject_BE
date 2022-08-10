package com.codegym.hongheo.core.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginForm {
    @NotNull
    @Size(min = 6, max = 20)
    private String username;

    @NotNull
    @Size(min = 6, max = 30)
    private String password;
}