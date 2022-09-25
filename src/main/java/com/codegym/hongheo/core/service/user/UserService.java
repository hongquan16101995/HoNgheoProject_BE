package com.codegym.hongheo.core.service.user;

import com.codegym.hongheo.core.model.dto.RoleConstant;
import com.codegym.hongheo.core.model.dto.SignUpForm;
import com.codegym.hongheo.core.model.entity.Role;
import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
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
        user.setEmail(signUpForm.getEmail());
        user.setPhone(signUpForm.getPhone());
        user.getRoles().add(new Role(RoleConstant.USER, RoleConstant.ROLE_USER));
        return userRepository.save(user);
    }
}
