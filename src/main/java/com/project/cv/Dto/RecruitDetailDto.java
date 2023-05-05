package com.project.cv.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruitDetailDto {
    private int id;
    private CompaniesDto company;
    private String salary;
    private String position;
    private AddressDetailDto address;
    private String experience;
    private long countdown;
}
