package com.codecomet.projects.airBnbApp.dto;

import com.codecomet.projects.airBnbApp.annotation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class SignUpRequestDto {

    @Email
    @NotBlank
    private String email;
    @ValidPassword
    @NotBlank
    private String password;
    @NotBlank
    private String name;
}
