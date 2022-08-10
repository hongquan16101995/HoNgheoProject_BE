package com.codegym.hongheo.core.repository;

import com.codegym.hongheo.core.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String name);
}
