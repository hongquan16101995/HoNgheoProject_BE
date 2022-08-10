package com.codegym.hongheo.core.controller;

import com.codegym.hongheo.core.model.dto.RoleDTO;
import com.codegym.hongheo.core.model.entity.Role;
import com.codegym.hongheo.core.service.role.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('GET_ROLES')")
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    public ResponseEntity<Role> createNewRole(@Validated @RequestBody RoleDTO roleDTO) {
        Role role = convertToEntity(roleDTO);
        return new ResponseEntity<>(roleService.save(role), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('GET_ROLE')")
    public ResponseEntity<Role> getRole(@PathVariable Long id) {
        Optional<Role> roleOptional = roleService.findById(id);
        return roleOptional.map(role -> new ResponseEntity<>(role, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id,@Validated @RequestBody RoleDTO roleDTO) {
        Optional<Role> roleOptional = roleService.findById(id);
        if (!roleOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return roleOptional.map(role -> {
            Role newRole = convertToEntity(roleDTO);
            newRole.setId(role.getId());
            return new ResponseEntity<>(convertToDto(roleService.save(newRole)), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    public ResponseEntity<Role> deleteRole(@PathVariable Long id) {
        Optional<Role> roleOptional = roleService.findById(id);
        if (!roleOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roleService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private RoleDTO convertToDto(Role Role) {
        return modelMapper.map(Role, RoleDTO.class);
    }

    private Role convertToEntity(RoleDTO RoleDTO) {
        return modelMapper.map(RoleDTO, Role.class);
    }
}
