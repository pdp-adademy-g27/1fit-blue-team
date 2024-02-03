package com.example.onefit.restrictions;

import com.example.onefit.common.service.GenericService;
import com.example.onefit.restrictions.dto.RestrictionCreateDto;
import com.example.onefit.restrictions.dto.RestrictionResponseDto;
import com.example.onefit.restrictions.dto.RestrictionUpdateDto;
import com.example.onefit.restrictions.entity.Restrictions;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class RestrictionService extends GenericService<Restrictions, UUID, RestrictionResponseDto, RestrictionCreateDto, RestrictionUpdateDto> {

    private final RestrictionRepository repository;
    private final Class<Restrictions> entityClass = Restrictions.class;
    private final RestrictionMapperDto mapper;

    @Override
    protected RestrictionResponseDto internalCreate(RestrictionCreateDto restrictionCreateDto) {
        Restrictions entity = mapper.toEntity(restrictionCreateDto);
        entity.setId(UUID.randomUUID());
        Restrictions saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected RestrictionResponseDto internalUpdate(UUID uuid, RestrictionUpdateDto restrictionUpdateDto) {
        Restrictions existingRestriction = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Restriction not found with id: " + uuid));

        mapper.toEntity(restrictionUpdateDto, existingRestriction);

        Restrictions updatedRestriction = repository.save(existingRestriction);

        return mapper.toResponseDto(updatedRestriction);
    }

    public void delete(UUID uuid) {
        Restrictions existingRestriction = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Restriction not found with id: " + uuid));

        repository.delete(existingRestriction);
    }

    public RestrictionResponseDto getById(UUID uuid) {
        Restrictions existingRestriction = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Restriction not found with id: " + uuid));

        return mapper.toResponseDto(existingRestriction);
    }

}
