package com.codegym.hongheo.core.repository;

import com.codegym.hongheo.core.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
