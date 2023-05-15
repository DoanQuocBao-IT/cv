package com.project.cv.Dto;

import com.project.cv.Model.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RecruitDto {
    private String profession;
    private String salary;
    private String position;
    private int inventory;
    private String gender;
    private String experience;

    private String responsibilities;
    private String qualifications;
    private String interests;
    private String address;
    private Date toDate;
}
