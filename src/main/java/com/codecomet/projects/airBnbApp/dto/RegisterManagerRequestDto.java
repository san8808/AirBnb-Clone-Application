package com.codecomet.projects.airBnbApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterManagerRequestDto {

    @NotBlank
    @Email
    String email;
    @NotBlank
    String name;
}
