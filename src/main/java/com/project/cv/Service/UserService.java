package com.project.cv.Service;

import com.project.cv.Dto.RegisterDto;
import com.project.cv.Dto.UserDto;
import com.project.cv.Dto.UsersDto;
import com.project.cv.Model.Candidates;
import com.project.cv.Model.Company;
import com.project.cv.Model.User;

public interface UserService {
    boolean addRegisterUser(RegisterDto registerDto);
    User updateUser(UserDto userDto);
    User getProfile();
    UsersDto getInformationUser();
    void changePassword(String password);

}
