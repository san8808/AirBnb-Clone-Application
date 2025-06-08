package com.codecomet.projects.airBnbApp.services;

import com.codecomet.projects.airBnbApp.dto.ProfileUpdateRequestDto;
import com.codecomet.projects.airBnbApp.dto.UserDto;
import com.codecomet.projects.airBnbApp.entity.User;

public interface UserService {

    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
