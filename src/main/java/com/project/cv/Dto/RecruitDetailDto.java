package com.project.cv.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruitDetailDto {
    private CompaniesDto company;
    private String position;
    private AddressDetailDto address;
    private String experience;
}
