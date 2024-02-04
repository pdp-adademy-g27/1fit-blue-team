package com.example.onefit.user.dto;

import com.example.onefit.user.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Gender gender;
    private UUID imageId;
    private LocalDate birthDate;
}
