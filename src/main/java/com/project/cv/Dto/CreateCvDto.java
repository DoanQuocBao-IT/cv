package com.project.cv.Dto;

import com.project.cv.Model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCvDto {
    private String goals;
    private String study;
    private String work;
    private String skill;
    private String prize;
    private String certificate;

    private String profession;
    private String position;
    private String experience;
    private Address address;
}
