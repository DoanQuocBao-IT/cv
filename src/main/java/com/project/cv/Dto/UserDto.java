package com.project.cv.Dto;

import com.project.cv.Model.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {
    private String fname;
    private String image;
    private String email;
    private String phone;
    private String website;
    private Address address;
}
