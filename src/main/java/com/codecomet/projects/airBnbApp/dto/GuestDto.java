package com.codecomet.projects.airBnbApp.dto;

import com.codecomet.projects.airBnbApp.entity.User;
import com.codecomet.projects.airBnbApp.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestDto {

    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
