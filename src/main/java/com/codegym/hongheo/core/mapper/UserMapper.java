package com.codegym.hongheo.core.mapper;

import com.codegym.hongheo.core.model.dto.UserDTO;
import com.codegym.hongheo.core.model.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {})
@Component
public interface UserMapper extends EntityMapper<UserDTO, User> {
}
