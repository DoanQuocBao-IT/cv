package com.project.cv.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompaniesDto {
    private Integer id;
    private UsersDto company;
    private String field;
    private int inventoryJob;
}
