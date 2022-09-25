package com.codegym.hongheo.wallet.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WalletDTO {
    private Long id;

    @NotNull
    private String icon;

    @NotNull
    private String name;

    @NotNull
    private int status;

    @NotNull
    private double money;
}
