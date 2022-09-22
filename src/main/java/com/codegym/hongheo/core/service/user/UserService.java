package com.codegym.hongheo.core.service.user;

import com.codegym.hongheo.core.exception.NotFoundException;
import com.codegym.hongheo.core.mapper.UserMapper;
import com.codegym.hongheo.core.model.dto.RoleConstant;
import com.codegym.hongheo.core.model.dto.SignUpForm;
import com.codegym.hongheo.core.model.dto.UserDTO;
import com.codegym.hongheo.core.model.entity.Role;
import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserDTO> findAll() {
        return mapper.toDto(userRepository.findAll());
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("not_found"));
        return mapper.toDto(user);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return mapper.toDto(userRepository.save(mapper.toEntity(userDTO)));
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public User findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElse(null);
    }

    @Override
    public User register(SignUpForm signUpForm) {
        User user = new User();
        user.setUsername(signUpForm.getUsername());
        user.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        user.getRoles().add(new Role(RoleConstant.USER, RoleConstant.ROLE_USER));
        return userRepository.save(user);
    }
}
