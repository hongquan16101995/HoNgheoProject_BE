package com.codegym.hongheo.core.mapper.impl;

import com.codegym.hongheo.core.mapper.IMapper;
import com.codegym.hongheo.core.model.dto.UserDTO;
import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.service.user.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements IMapper<User, UserDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IUserService iUserService;

    @Override
    public User convertToE(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public UserDTO convertToD(User user) {
        if (iUserService.findById(user.getId()).isPresent()) {
            return modelMapper.map(iUserService.findById(user.getId()).get(), UserDTO.class);
        }
        return null;
    }
}
