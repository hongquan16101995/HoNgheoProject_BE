package com.codegym.hongheo.core.controller;

import com.codegym.hongheo.core.model.dto.LoginForm;
import com.codegym.hongheo.core.model.dto.LoginResponse;
import com.codegym.hongheo.core.model.dto.SignUpForm;
import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.service.auth.JwtService;
import com.codegym.hongheo.core.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AuthController{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(loginForm.getUsername());
        LoginResponse loginResponse = new LoginResponse(jwt, currentUser.getId(), currentUser.getUsername(), currentUser.getName(), userDetails.getAuthorities());
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Validated @RequestBody SignUpForm signUpForm) {
        return new ResponseEntity<>(userService.register(signUpForm), HttpStatus.OK);
    }
}
