package com.codegym.hongheo.core.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String phone;
}
