package com.project.cv.Dto;

import com.project.cv.Model.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CandidateDto {
    private String fname;
    private String image;
    private Date birthday;
    private String gender;
    private String phone;
    private String website;
    private Address address;
}
