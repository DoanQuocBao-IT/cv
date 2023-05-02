package com.project.cv.Dto;

import com.project.cv.Model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CompanyDto {
    private User company;
    private Date foundedAt;
    private String information;
    private String field;
}
