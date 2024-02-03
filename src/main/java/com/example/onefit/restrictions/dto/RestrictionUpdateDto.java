package com.example.onefit.restrictions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestrictionUpdateDto {

    private String name;
    private String description;
}
