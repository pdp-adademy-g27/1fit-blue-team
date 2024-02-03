package com.example.onefit.restrictions;

import com.example.onefit.common.repository.GenericRepository;
import com.example.onefit.restrictions.entity.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestrictionRepository extends GenericRepository<Restrictions, UUID> {
}
