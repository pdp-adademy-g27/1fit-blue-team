package com.example.onefit.role.permission;

import com.example.onefit.common.repository.GenericRepository;
import com.example.onefit.role.permission.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface PermissionRepository extends GenericRepository<Permission, UUID> {
    Set<Permission>findAllByNameIn(Set<String>names);
}
