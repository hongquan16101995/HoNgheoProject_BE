package com.codegym.hongheo.core.mapper;

import com.codegym.hongheo.core.model.dto.UserDTO;
import com.codegym.hongheo.core.model.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface IUserMapper extends EntityMapper<UserDTO, User> {
}
