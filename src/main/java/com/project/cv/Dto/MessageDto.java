package com.project.cv.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageDto {
    private int id;
    private UserSenderDto sender;

    private String content;
    private Date createAt;
    private UserSenderDto receiver;
}
