package com.example.onefit.restrictions;

import com.example.onefit.common.mapper.GenericMapper;
import com.example.onefit.restrictions.dto.RestrictionCreateDto;
import com.example.onefit.restrictions.dto.RestrictionResponseDto;
import com.example.onefit.restrictions.dto.RestrictionUpdateDto;
import com.example.onefit.restrictions.entity.Restrictions;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RestrictionMapperDto extends GenericMapper<Restrictions, RestrictionCreateDto, RestrictionResponseDto, RestrictionUpdateDto> {
    private final ModelMapper modelMapper;

    @Override
    public Restrictions toEntity(RestrictionCreateDto restrictionCreateDto) {
        return modelMapper.map(restrictionCreateDto, Restrictions.class);
    }

    @Override
    public RestrictionResponseDto toResponseDto(Restrictions restrictions) {
        return modelMapper.map(restrictions, RestrictionResponseDto.class);
    }

    @Override
    public void toEntity(RestrictionUpdateDto restrictionUpdateDto, Restrictions restrictions) {
        modelMapper.map(restrictionUpdateDto, restrictions);
    }
}
