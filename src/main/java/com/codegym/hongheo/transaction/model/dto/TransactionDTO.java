package com.codegym.hongheo.transaction.model.dto;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class TransactionDTO {
    private Long id;

    @NotNull
    private double total;

    private LocalDateTime time;

    @NotNull
    private String description;

    @NotNull
    private Set<CategoryDTO> categoryDTOSet;
}
