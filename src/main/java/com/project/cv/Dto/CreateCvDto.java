package com.project.cv.Dto;

import com.project.cv.Model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCvDto {
    private String profession;
    private String position;
    private String experience;
    private Address address;
}
