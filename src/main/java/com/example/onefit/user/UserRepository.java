package com.example.onefit.user;

import com.example.onefit.common.repository.GenericRepository;
import com.example.onefit.user.entity.User;

import java.util.UUID;

public interface UserRepository extends GenericRepository<User, UUID> {
}
