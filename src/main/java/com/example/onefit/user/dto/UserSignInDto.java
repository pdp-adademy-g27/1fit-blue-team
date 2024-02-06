package com.example.onefit.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInDto {
    @NotNull
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @NotNull
    private String password;
}
