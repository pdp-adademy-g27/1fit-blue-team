package com.example.onefit.role;

import com.example.onefit.common.repository.GenericRepository;
import com.example.onefit.role.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface  RoleRepository extends GenericRepository<Role, UUID> {
    Optional<Role>findByName(String name);
}
