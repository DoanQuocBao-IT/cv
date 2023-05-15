package com.project.cv.Dto;

import com.project.cv.Model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UsersDto {
    private Integer id;
    private String fname;
    private String image;
    private Set<Role> roles;
}
