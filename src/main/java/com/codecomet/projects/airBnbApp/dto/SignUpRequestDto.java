package com.codecomet.projects.airBnbApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class SignUpRequestDto {

    private String email;
    private String password;
    private String name;
}
