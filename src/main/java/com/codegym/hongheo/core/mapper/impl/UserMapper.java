package com.codegym.hongheo.core.mapper.impl;

import com.codegym.hongheo.core.mapper.IMapper;
import com.codegym.hongheo.core.model.dto.UserDTO;
import com.codegym.hongheo.core.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements IMapper<User, UserDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User convertToE(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @Override
    public UserDTO convertToD(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
