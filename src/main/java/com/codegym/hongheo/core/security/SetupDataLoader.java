package com.codegym.hongheo.core.filter.security;

import com.codegym.hongheo.core.model.dto.PermissionEnum;
import com.codegym.hongheo.core.model.entity.Permission;
import com.codegym.hongheo.core.model.entity.Role;
import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.repository.IPermissionRepository;
import com.codegym.hongheo.core.repository.IRoleRepository;
import com.codegym.hongheo.core.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IPermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup){
            return;
        }
        Set<Permission> adminPermissions = new HashSet<>();
        for (PermissionEnum name : PermissionEnum.values()) {
            Permission permission = createPermissionIfNotFound(name.toString());
            adminPermissions.add(permission);
        }
        Permission getUsers = permissionRepository.findByName(PermissionEnum.GET_USERS.toString());
        Permission getUser = permissionRepository.findByName(PermissionEnum.GET_USER.toString());
        Permission getRoles = permissionRepository.findByName(PermissionEnum.GET_ROLES.toString());
        Permission getRole = permissionRepository.findByName(PermissionEnum.GET_ROLE.toString());
        Set<Permission> userPermissions = new HashSet<>();
        userPermissions.add(getUsers);
        userPermissions.add(getUser);
        userPermissions.add(getRoles);
        userPermissions.add(getRole);
        createRoleIfNotFound("ROLE_ADMIN", adminPermissions);
        createRoleIfNotFound("ROLE_USER", userPermissions);
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        createUserIfNotFound("system", roles);
        alreadySetup = true;
    }

    @Transactional
    public Permission createPermissionIfNotFound(String name) {
        Permission permission = permissionRepository.findByName(name);
        if (permission == null) {
            permission = new Permission();
            permission.setName(name);
            permissionRepository.save(permission);
        }
        return permission;
    }

    @Transactional
    public void createRoleIfNotFound(String name, Set<Permission> permissions) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setPermissions(permissions);
        }
        role.setPermissions(permissions);
        roleRepository.save(role);
    }
    @Transactional
    public void createUserIfNotFound(String username, Set<Role> roles) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()){
            User user = new User();
            user.setName("Admin");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setUsername("system");
            user.setPhone("012345678");
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
