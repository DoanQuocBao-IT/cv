package com.project.cv.Service;

import com.project.cv.Dto.RegisterDto;
import com.project.cv.Dto.UserDto;
import com.project.cv.Model.User;

public interface UserService {
    boolean addRegisterUser(RegisterDto registerDto);
    User updateUser(UserDto userDto);
    User getProfile();
}
