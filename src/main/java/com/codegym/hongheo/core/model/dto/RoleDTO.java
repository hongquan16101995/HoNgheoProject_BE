package com.codegym.hongheo.core.model.dto;


import com.codegym.hongheo.core.model.entity.Permission;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class RoleDTO {
    private Long id;

    @NotNull
    private String name;
    
    private Set<Permission> permissions;
}
