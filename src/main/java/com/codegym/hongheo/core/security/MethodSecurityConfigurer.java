package com.codegym.hongheo.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/*
* prePostEnable: using @PostSecure and Presecure
* securedEnable: using @Secure
* jsr250Enable: using @RoleAllow
* */
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class MethodSecurityConfigurer extends GlobalMethodSecurityConfiguration {
}
