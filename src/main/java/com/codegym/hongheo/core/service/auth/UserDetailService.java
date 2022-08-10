package com.codegym.hongheo.core.service.auth;

import com.codegym.hongheo.core.security.UserDetail;
import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements IUserDetailService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Username is not existed!");
        }
        return UserDetail.build(userOptional.get());
    }
}
