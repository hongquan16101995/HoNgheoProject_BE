package com.codegym.hongheo.category.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDTO {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String color;

    @NotNull
    private String description;

    @NotNull
    private boolean status;
}
