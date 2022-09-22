package com.codegym.hongheo.core.service.user;


import com.codegym.hongheo.core.model.dto.SignUpForm;
import com.codegym.hongheo.core.model.dto.UserDTO;
import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.service.ICoreService;

public interface IUserService extends ICoreService<UserDTO, Long> {

    User findByUsername(String username);

    User register(SignUpForm signUpForm);
}
