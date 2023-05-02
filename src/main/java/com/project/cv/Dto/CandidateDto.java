package com.project.cv.Dto;

import com.project.cv.Model.Address;
import com.project.cv.Model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CandidateDto {
    private User candidate;
    private Date birthday;
    private String gender;
    private String introduce;
    private String hobby;
    private String certificate;
}
