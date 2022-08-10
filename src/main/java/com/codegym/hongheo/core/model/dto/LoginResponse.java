package com.codegym.hongheo.core.model.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class LoginResponse {
    private Long id;
    private String token;

    private String type = "Bearer";
    private String username;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;

    public LoginResponse(String accessToken, Long id, String username, String name, Collection<? extends GrantedAuthority> authorities) {
        this.token = accessToken;
        this.username = username;
        this.authorities = authorities;
        this.name = name;
        this.id = id;
    }
}
